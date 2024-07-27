package tech.badprogrammer.util;

import tech.badprogrammer.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactManager {

    private final List<Contact> contacts = new ArrayList<>();

    public Contact addContact(Contact contact) {
        contacts.add(contact);
        final Optional<Contact> result = getContactById(contact.getId());
        return result.orElseGet(null);
    }

    public Optional<Contact> getContactById(int id) {
        final Optional<Contact> optionalContact = contacts.stream()
                                                          .filter(contact -> contact.getId() == id)
                                                          .findFirst();
        return optionalContact;
    }
}
