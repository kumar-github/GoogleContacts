package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ContactManager {

    private static final Logger         LOGGER                      = Logger.getLogger(ContactManager.class.getName());
    private static final String         DEFAULT_CONTACTS_FILE       = "src/main/resources/contacts.bin";
    private static final String         DEFAULT_PROFILE_PICS_FOLDER = "src/main/resources/profilepics";
    private static final String         FORWARD_SLASH               = "/";
    private static final String         DOT                         = ".";
    private static       ContactManager INSTANCE                    = null;
    private final        ContactUtil    contactUtil                 = ContactUtil.getInstance();
    private final        AddressUtil    addressUtil                 = AddressUtil.getInstance();
    private              String         contactsFilePath;
    private              List<Contact>  contacts;

    private ContactManager(String contactsFilePath) {
        setContactsFilePath(contactsFilePath);
        registerContactWriterShutdownHook();
        loadContactsFromFile(contactsFilePath);
    }

    public static ContactManager getInstance() {
        return getInstance(DEFAULT_CONTACTS_FILE);
    }

    public static ContactManager getInstance(String filePath) {
        if (INSTANCE == null) {
            INSTANCE = new ContactManager(filePath);
            LOGGER.log(Level.INFO, "New ContactManager instance {0} created.", new Object[]{ INSTANCE });
        }
        return INSTANCE;
    }

    public List<Contact> getAllContacts() {
        LOGGER.info("Returning all contacts.");
        return contacts;
    }

    public Optional<Contact> getContactById(int id) {
        LOGGER.log(Level.INFO, "Trying to find contact with id {0}.", new Object[]{ id });
        return contacts.stream()
                       .filter(contact -> contact.getId() == id)
                       .findFirst();
    }

    public Contact addContact(Contact contact) {
        if (contact.getId() != 0) {
            throw new IllegalArgumentException("New contact should not have ID set.");
        }

        int id = contactUtil.generateNewContactId();
        contact.setId(id);

        final String processedProfilePicturePath = createProfilePicture(contact);
        contact.setProcessedProfilePicture(processedProfilePicturePath);

        final LocalDate processedBirthday = processBirthdayDate(contact);
        contact.setProcessedBirthday(processedBirthday);

        final LocalDate processedSignificantDate = processSignificantDate(contact);
        contact.setProcessedSignificantDate(processedSignificantDate);

        if (addressAvailable(contact)) {
            final int addressId = addressUtil.generateNewAddressId();
            contact.getAddress()
                   .setId(addressId);
        }

        contacts.add(contact);
        LOGGER.log(Level.INFO, "New Contact with id {0} created successfully.", new Object[]{ id });

        final Optional<Contact> result = getContactById(id);
        return result.orElseThrow();
    }

    public Contact updateContact(Contact contact) {
        if (contact.getId() == 0) {
            throw new IllegalArgumentException("Existing contact should have ID set.");
        }
        final boolean isRemoved = contacts.removeIf(c -> c.getId() == contact.getId());
        if (!isRemoved) {
            LOGGER.log(Level.SEVERE, "Contact with id {0} does not exist.", new Object[]{ contact.getId() });
            return null;
        }
        contacts.add(contact);
        LOGGER.log(Level.INFO, "Contact with id {0} updated successfully.", new Object[]{ contact.getId() });
        final Optional<Contact> result = getContactById(contact.getId());
        return result.orElseThrow();
    }

    public void deleteContact(int id) {
        final boolean isRemoved = contacts.removeIf(c -> c.getId() == id);
        if (isRemoved) {
            LOGGER.log(Level.INFO, "Contact with id {0} deleted successfully.", new Object[]{ id });
        }
        else {
            LOGGER.log(Level.WARNING, "Contact with id {0} does not exists.", new Object[]{ id });
        }
    }

    public void deleteAllContacts() {
        contacts.clear();
        LOGGER.info("All contacts deleted successfully.");
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private void registerContactWriterShutdownHook() {
        final Thread contactWriter = new Thread(new ContactWritingTask(this));
        Runtime.getRuntime()
               .addShutdownHook(contactWriter);
        LOGGER.info("Contact Writer Shutdown Hook registered successfully.");
    }

    private void loadContactsFromFile(final String contactsFile) {
        final String contactsFileName = extractFileNameFromPath(contactsFile);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(contactsFile))) {
            final List<Contact> contacts = (List<Contact>) objectInputStream.readObject();
            this.contacts = new ArrayList<>(contacts);
            LOGGER.log(Level.INFO, "\"{0}\" file FOUND. Loaded contacts from file.", new Object[]{ contactsFileName });
        }
        catch (FileNotFoundException exception) {
            /*
             The contacts file does not exists. May be the app is launched for the first time, so let's
             create an empty list.
            */
            LOGGER.log(Level.INFO, "\"{0}\" file NOT FOUND. Creating empty contacts list.",
                       new Object[]{ contactsFileName });
            this.contacts = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static String extractFileNameFromPath(String filePath) {
        final String[] tokens = filePath.split(FORWARD_SLASH);
        return tokens[tokens.length - 1];
    }

    private static String extractExtension(String fileName) {
        final String[] tokens = fileName.split(Pattern.quote(DOT));
        return tokens[tokens.length - 1];
    }

    private void setContactsFilePath(String contactsFilePath) {
        this.contactsFilePath = contactsFilePath;
    }

    public String getContactsFilePath() {
        return contactsFilePath;
    }

    private String createProfilePicture(Contact contact) {
        if (!profilePictureAvailable(contact)) {
            return null;
        }
        final File sourceProfilePicFile = new File(contact.getProfilePicture());
        if (!sourceProfilePicFile.exists()) {
            throw new IllegalArgumentException("Profile pic file does not exist.");
        }
        final byte[] bytes       = readProfilePictureFile(sourceProfilePicFile);
        String       newFileName = generateProfilePictureFileName(contact);
        writeProfilePicture(newFileName, bytes);
        Path profilePicturePath = Path.of(DEFAULT_PROFILE_PICS_FOLDER, newFileName);
        return profilePicturePath.toString();
    }

    private byte[] readProfilePictureFile(final File profilePicFile) {
        try (FileInputStream fileInputStream = new FileInputStream(profilePicFile)) {
            return fileInputStream.readAllBytes();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void writeProfilePicture(final String newFileName, final byte[] profilePictureBytes) {
        String profilePictureFile = Path.of(DEFAULT_PROFILE_PICS_FOLDER, newFileName)
                                        .toString();
        try (FileOutputStream fileOutputStream = new FileOutputStream(profilePictureFile)) {
            fileOutputStream.write(profilePictureBytes);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static String generateProfilePictureFileName(Contact contact) {
        final String profilePicturePath = contact.getProfilePicture();
        String       extension          = extractExtension(extractFileNameFromPath(profilePicturePath));
        final String newFileName        = contact.getId() + DOT + extension;
        return newFileName;
    }

    private LocalDate processBirthdayDate(Contact contact) {
        if (!birthdayAvailable(contact)) {
            return null;
        }
        final DateTimeFormatter formatter         = contactUtil.dateWithOrWithoutYearFormatter();
        final LocalDate         processedBirthday = LocalDate.parse(contact.getBirthday(), formatter);
        return processedBirthday;
    }

    private LocalDate processSignificantDate(final Contact contact) {
        if (!significantDateAvailable(contact)) {
            return null;
        }
        final DateTimeFormatter formatter                = contactUtil.dateWithOrWithoutYearFormatter();
        final LocalDate         processedSignificantDate = LocalDate.parse(contact.getSignificantDate(), formatter);
        return processedSignificantDate;
    }

    private boolean profilePictureAvailable(Contact contact) {
        return contact.getProfilePicture() != null && !contact.getProfilePicture()
                                                              .isBlank();
    }

    private boolean birthdayAvailable(Contact contact) {
        return contact.getBirthday() != null && !contact.getBirthday()
                                                        .isBlank();
    }

    private boolean addressAvailable(final Contact contact) {
        return contact.getAddress() != null;
    }

    private boolean significantDateAvailable(final Contact contact) {
        return contact.getSignificantDate() != null && !contact.getSignificantDate()
                                                               .isBlank();
    }
}
