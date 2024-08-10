package tech.badprogrammer.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.badprogrammer.model.Contact;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class ContactManagerTest {

    private              ContactManager contactManager;
    private static final String         contactsFilePath = "src/test/resources/contacts-test.bin";

    @BeforeAll
    static void beforeAll() {
        deleteContactsFile();
    }

    @BeforeEach
    void beforeEach() {
        contactManager = ContactManager.getInstance(contactsFilePath);
    }

    @Nested
    class ContactListSpecification {

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
        class An_empty_contact_list {

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

            @Test
            void size_should_be_one_after_creating_a_single_contact() {
                final Contact john = createNewContact("John", "Doe", "+911111111111");
                contactManager.addContact(john);
                final int expectedSize = 1;
                final int actualSize = contactManager.getAllContacts()
                                                     .size();
                Assertions.assertEquals(expectedSize, actualSize, "Contact list size is not one.");
                contactManager.deleteAllContacts();
            }
        }

        @Nested
        class A_non_empty_contact_list {

            @Test
            void size_should_not_be_zero() {
                final Contact john = createNewContact("John", "Doe", "+911111111111");
                contactManager.addContact(john);
                final int expectedSize = 1;
                final int actualSize = contactManager.getAllContacts()
                                                     .size();
                Assertions.assertEquals(expectedSize, actualSize, "Contact list size is not one.");
                contactManager.deleteAllContacts();
            }

            @Test
            void should_not_return_an_empty_optional_when_asked_for_a_specific_contact() {
            }
        }
    }

    // @Test
    // void add_contact() {
    //     final Contact john = new Contact();
    //     // john.setId(1001);
    //     john.setFirstName("John");
    //     john.setLastName("Doe");
    //     john.setPhone("+910000000000");
    //     final Contact result = contactManager.addContact(john);
    //     Assertions.assertNotNull(result, "Contact was not added.");
    //     Assertions.assertEquals(john, result, "Contact was either not added or not same.");
    // }
    //
    // @Test
    // void delete_contact() {
    //     // Arrange
    //     final Contact john = new Contact();
    //     john.setFirstName("John");
    //     john.setLastName("Doe");
    //     john.setPhone("+910000000000");
    //     final Contact result = contactManager.addContact(john);
    //
    //     // Act
    //     contactManager.deleteContact(john.getId());
    //
    //     // Assert
    //     final Optional<Contact> optionalContact = contactManager.getContactById(john.getId());
    //     Assertions.assertTrue(optionalContact.isEmpty(), "Contact was not deleted.");
    // }
    //
    // // @Nested
    // // class A_new_contact_list {
    // //
    // //     @Test
    // //     void should_be_empty() {
    // //         final List<Contact> contacts = contactManager.getAllContacts();
    // //         Assertions.assertTrue(contacts.isEmpty(), "Contact list is not empty.");
    // //     }
    // //
    // //     @Test
    // //     void size_should_be_zero() {
    // //         final List<Contact> contacts = contactManager.getAllContacts();
    // //         Assertions.assertEquals(0, contacts.size(), "Contact list size is not zero.");
    // //     }
    // //
    // //     @Test
    // //     void should_return_an_empty_optional_when_asked_for_a_specific_contact() {
    // //         final Optional<Contact> optionalContact = contactManager.getContactById(1);
    // //         Assertions.assertTrue(optionalContact.isEmpty(), "Contact is not present.");
    // //     }
    // // }
    //
    // // @Nested
    // // class An_empty_contact_list {}
    // //
    // // @Nested
    // // class A_non_empty_contact_list {}

    @AfterEach
    void afterEach() {
        // delete all contacts
    }

    @AfterAll
    static void afterAll() {
        deleteContactsFile();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private Contact createNewContact(String firstName, String lastName, String phone) {
        final Contact result = new Contact();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setPhone(ImmutablePair.of(phone, "Mobile"));
        return result;
    }

    private static void deleteContactsFile() {
        try {
            Files.deleteIfExists(Path.of(contactsFilePath));
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
