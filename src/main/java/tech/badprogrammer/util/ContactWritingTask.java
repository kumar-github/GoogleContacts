package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactWritingTask implements Runnable {

    private static final Logger        LOGGER        = Logger.getLogger(ContactWritingTask.class.getName());
    private static final String        CONTACTS_FILE = "contacts.bin";
    private final        List<Contact> contacts;

    public ContactWritingTask(List<Contact> contacts) {
        // this.contacts = List.copyOf(contacts);
        this.contacts = contacts;
    }

    @Override
    public void run() {
        writeContactsToFile(this.contacts);
    }

    private void writeContactsToFile(List<Contact> contacts) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(CONTACTS_FILE))) {
            objectOutputStream.writeObject(contacts);
            LOGGER.log(Level.INFO, "{0} contacts successfully written to file {1}.",
                       new Object[]{ contacts.size(), CONTACTS_FILE });
            System.out.format("%d contacts successfully written to file \"%s\"", contacts.size(), CONTACTS_FILE);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
