package tech.badprogrammer;

import tech.badprogrammer.model.Contact;
import tech.badprogrammer.util.ContactManager;

public class Application {

    private static final String LOGGING_CONFIG_FILE_PROPERTY_KEY = "java.util.logging.config.file";

    static {
        final String LOGGING_CONFIG_FILE_PATH = Application.class.getClassLoader()
                                                                 .getResource("logging.properties")
                                                                 .getFile();
        System.setProperty(LOGGING_CONFIG_FILE_PROPERTY_KEY, LOGGING_CONFIG_FILE_PATH);
    }

    public static void main(String[] args) {
        final ContactManager contactManager = new ContactManager();

        // should be empty
        System.out.println(contactManager.getAllContacts());

        final Contact john = new Contact();
        // john.setId(1001);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        System.out.println(contactManager.addContact(john));

        final Contact marius = new Contact();
        // marius.setId(1002);
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone("+911111111111");
        System.out.println(contactManager.addContact(marius));

        final Contact david = new Contact();
        // david.setId(1003);
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone("+912222222222");
        System.out.println(contactManager.addContact(david));

        final Contact chris = new Contact();
        // chris.setId(1004);
        chris.setFirstName("Chris");
        chris.setLastName("Wayne");
        chris.setPhone("+913333333333");
        System.out.println(contactManager.addContact(chris));

        final Contact bruce = new Contact();
        // bruce.setId(1004);
        bruce.setFirstName("Bruce");
        bruce.setLastName("Wayne");
        bruce.setPhone("+914444444444");
        contactManager.addContact(bruce);

        // should have 5 contacts
        System.out.println(contactManager.getAllContacts());
    }
}
