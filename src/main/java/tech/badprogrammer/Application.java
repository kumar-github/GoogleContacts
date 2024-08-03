package tech.badprogrammer;

import tech.badprogrammer.model.Contact;
import tech.badprogrammer.util.ContactManager;

import java.util.logging.Logger;

public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    static {
        final String LOGGING_CONFIG_FILE_PROPERTY_KEY = "java.util.logging.config.file";
        final String LOGGING_CONFIG_FILE_PATH = Application.class.getClassLoader()
                                                                 .getResource("logging.properties")
                                                                 .getFile();
        System.setProperty(LOGGING_CONFIG_FILE_PROPERTY_KEY, LOGGING_CONFIG_FILE_PATH);
    }

    public static void main(String[] args) {
        final ContactManager contactManager = ContactManager.getInstance();

        // should be empty
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        final Contact john = new Contact();
        // john.setId(1001);
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        LOGGER.info(contactManager.addContact(john)
                                  .toString());

        final Contact marius = new Contact();
        // marius.setId(1002);
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone("+911111111111");
        LOGGER.info(contactManager.addContact(marius)
                                  .toString());

        final Contact david = new Contact();
        // david.setId(1003);
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone("+912222222222");
        LOGGER.info(contactManager.addContact(david)
                                  .toString());

        final Contact chris = new Contact();
        // chris.setId(1004);
        chris.setFirstName("Chris");
        chris.setLastName("Wayne");
        chris.setPhone("+913333333333");
        LOGGER.info(contactManager.addContact(chris)
                                  .toString());

        final Contact bruce = new Contact();
        // bruce.setId(1004);
        bruce.setFirstName("Bruce");
        bruce.setLastName("Wayne");
        bruce.setPhone("+914444444444");
        LOGGER.info(contactManager.addContact(bruce)
                                  .toString());

        // should have 5 contacts
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        chris.setLastName("Mudry");
        chris.setPhone("+915555555555");
        System.out.println(contactManager.updateContact(chris));
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        contactManager.deleteContact(4);

        LOGGER.info(contactManager.getAllContacts()
                                  .toString());
    }
}
