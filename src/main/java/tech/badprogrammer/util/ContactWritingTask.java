package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactWritingTask implements Runnable {

    private static final Logger         LOGGER        = Logger.getLogger(ContactWritingTask.class.getName());
    private static final String         FORWARD_SLASH = "/";
    private final        ContactManager contactManager;

    public ContactWritingTask(ContactManager contactManager) {
        this.contactManager = contactManager;
    }

    @Override
    public void run() {
        writeContactsToFile(contactManager.getAllContacts());
    }

    private void writeContactsToFile(List<Contact> contacts) {
        final String contactsFilePath = contactManager.getContactsFilePath();
        final String contactsFileName = extractFileNameFromPath(contactsFilePath);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(contactsFilePath))) {
            objectOutputStream.writeObject(contacts);
            LOGGER.log(Level.INFO, "{0} contacts successfully written to file {1}.",
                       new Object[]{ contacts.size(), contactsFileName });
            System.out.format("%d contacts successfully written to file \"%s\"", contacts.size(), contactsFileName);
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static String extractFileNameFromPath(String filePath) {
        final String[] tokens = filePath.split(FORWARD_SLASH);
        return tokens[tokens.length - 1];
    }
}
