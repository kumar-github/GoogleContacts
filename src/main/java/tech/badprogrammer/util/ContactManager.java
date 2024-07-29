package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactManager {

    private final ContactUtil   contactUtil = new ContactUtil();
    private final List<Contact> contacts    = new ArrayList<>();

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
}
