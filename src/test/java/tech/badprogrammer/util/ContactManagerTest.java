package tech.badprogrammer.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.badprogrammer.model.Contact;

import java.util.List;
import java.util.Optional;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class ContactManagerTest {

    private ContactManager contactManager;

    @BeforeEach
    void setUp() {
        contactManager = new ContactManager();
    }

    @Test
    void add_contact() {
        final Contact john = new Contact();
        // john.setId(1001);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        final Contact result = contactManager.addContact(john);
        Assertions.assertNotNull(result, "Contact was not added.");
        Assertions.assertEquals(john, result, "Contact was either not added or not same.");
    }

    @Nested
    class A_new_contact_list {

        @Test
        void should_be_empty() {
            final List<Contact> contacts = contactManager.getAllContacts();
            Assertions.assertTrue(contacts.isEmpty(), "Contact list is not empty.");
        }

        @Test
        void size_should_be_zero() {
            final List<Contact> contacts = contactManager.getAllContacts();
            Assertions.assertEquals(0, contacts.size(), "Contact list size is not zero.");
        }

        @Test
        void should_return_an_empty_optional_when_asked_for_a_specific_contact() {
            final Optional<Contact> optionalContact = contactManager.getContactById(1);
            Assertions.assertTrue(optionalContact.isEmpty(), "Contact is not present.");
        }
    }

    @Nested
    class An_empty_contact_list {}

    @Nested
    class A_non_empty_contact_list {}
}
