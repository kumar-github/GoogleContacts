package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactManager {

    private static final String        CONTACTS_FILE = "contacts.bin";
    private final        ContactUtil   contactUtil   = new ContactUtil();
    private              List<Contact> contacts;

    public ContactManager() {
        init();
    }

    private void init() {
        loadContactsFromFile();
        registerContactWriterShutdownHook();
    }

    public Contact addContact(Contact contact) {
        if (contact.getId() != 0) {
            throw new IllegalArgumentException("New contact should not have ID set.");
        }
        int id = contactUtil.generateNewContactId();
        contact.setId(id);
        contacts.add(contact);
        final Optional<Contact> result = getContactById(id);
        return result.orElseThrow();
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public Optional<Contact> getContactById(int id) {
        return contacts.stream()
                       .filter(contact -> contact.getId() == id)
                       .findFirst();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private void registerContactWriterShutdownHook() {
        final Thread contactWriter = new Thread(new ContactWritingTask(getAllContacts()));
        Runtime.getRuntime()
               .addShutdownHook(contactWriter);
    }

    private void loadContactsFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(CONTACTS_FILE))) {
            final List<Contact> contacts = (List<Contact>) objectInputStream.readObject();
            this.contacts = new ArrayList<>(contacts);
        }
        catch (FileNotFoundException exception) {
            /*
            Seems, the contacts file does not exists. May be the user is launching the app for the first time, so
            let's create a an empty list.
             */
            System.out.format("File \"%s\" not found. Creating empty contacts list.", CONTACTS_FILE);
            this.contacts = new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }
}
