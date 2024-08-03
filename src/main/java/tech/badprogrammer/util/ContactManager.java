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

    private static final Logger         LOGGER        = Logger.getLogger(ContactManager.class.getName());
    private static final String         CONTACTS_FILE = "contacts.bin";
    private static       ContactManager INSTANCE      = null;
    private final        ContactUtil    contactUtil   = ContactUtil.getInstance();
    private              List<Contact>  contacts;

    private ContactManager() {
        init();
    }

    public static ContactManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactManager();
            LOGGER.log(Level.INFO, "New ContactManager instance {0} created.", new Object[]{ INSTANCE });
        }
        return INSTANCE;
    }

    private void init() {
        loadContactsFromFile();
        registerContactWriterShutdownHook();
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

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private void registerContactWriterShutdownHook() {
        final Thread contactWriter = new Thread(new ContactWritingTask(this));
        Runtime.getRuntime()
               .addShutdownHook(contactWriter);
        LOGGER.info("Contact Writer Shutdown Hook registered successfully.");
    }

    private void loadContactsFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(CONTACTS_FILE))) {
            final List<Contact> contacts = (List<Contact>) objectInputStream.readObject();
            this.contacts = new ArrayList<>(contacts);
            LOGGER.log(Level.INFO, "\"{0}\" file FOUND. Loaded contacts from file.", new Object[]{ CONTACTS_FILE });
        }
        catch (FileNotFoundException exception) {
            /*
            Seems, the contacts file does not exists. May be the user is launching the app for the first time, so
            let's create a an empty list.
             */
            LOGGER.log(Level.INFO, "\"{0}\" file NOT FOUND. Creating empty contacts list.",
                       new Object[]{ CONTACTS_FILE });
            this.contacts = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
