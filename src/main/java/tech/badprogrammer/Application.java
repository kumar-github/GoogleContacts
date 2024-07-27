package tech.badprogrammer;

import tech.badprogrammer.model.Contact;
import tech.badprogrammer.util.ContactManager;

public class Application {

    public static void main(String[] args) {
        final ContactManager contactManager = new ContactManager();

        // should be empty
        System.out.println(contactManager.getAllContacts());

        final Contact john = new Contact();
        john.setId(1001);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        System.out.println(contactManager.addContact(john));

        final Contact marius = new Contact();
        marius.setId(1002);
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone("+911111111111");
        System.out.println(contactManager.addContact(marius));

        final Contact david = new Contact();
        david.setId(1003);
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone("+913333333333");
        System.out.println(contactManager.addContact(david));

        // should have 3 contacts
        System.out.println(contactManager.getAllContacts());
    }
}
