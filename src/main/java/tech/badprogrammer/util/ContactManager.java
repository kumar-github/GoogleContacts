package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactManager {

    private static final Logger         LOGGER                = Logger.getLogger(ContactManager.class.getName());
    private static final String         DEFAULT_CONTACTS_FILE = "src/main/resources/contacts.bin";
    private static final String         FORWARD_SLASH         = "/";
    private static       ContactManager INSTANCE              = null;
    private final        ContactUtil    contactUtil           = ContactUtil.getInstance();
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

    private void setContactsFilePath(String contactsFilePath) {
        this.contactsFilePath = contactsFilePath;
    }

    public String getContactsFilePath() {
        return contactsFilePath;
    }
}
