package tech.badprogrammer;

import tech.badprogrammer.model.Contact;
import tech.badprogrammer.util.ContactManager;

import java.util.logging.Logger;

public class Application {

    static {
        initializeLogging();
    }

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        final ContactManager contactManager = ContactManager.getInstance();

        // should be empty
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        final Contact john = new Contact();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        john.setLabel("Work");

        LOGGER.info(contactManager.addContact(john)
                                  .toString());

        final Contact marius = new Contact();
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone("+911111111111");
        marius.setLabel("Friends");
        LOGGER.info(contactManager.addContact(marius)
                                  .toString());

        final Contact david = new Contact();
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone("+912222222222");
        david.setLabel("Gym");
        LOGGER.info(contactManager.addContact(david)
                                  .toString());

        final Contact chris = new Contact();
        chris.setFirstName("Chris");
        chris.setLastName("Wayne");
        chris.setPhone("+913333333333");
        chris.setLabel("Work");
        LOGGER.info(contactManager.addContact(chris)
                                  .toString());

        final Contact bruce = new Contact();
        bruce.setFirstName("Bruce");
        bruce.setLastName("Wayne");
        bruce.setPhone("+914444444444");
        bruce.setLabel("Gym");
        LOGGER.info(contactManager.addContact(bruce)
                                  .toString());

        // should have 5 contacts
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        chris.setLastName("Mudry");
        chris.setPhone("+915555555555");
        chris.setLabel("Gym");
        System.out.println(contactManager.updateContact(chris));
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        contactManager.deleteContact(4);

        LOGGER.info(contactManager.getAllContacts()
                                  .toString());
    }

    // =================================================================================================================
    // =================================================================================================================
    // =================================================================================================================

    private static void initializeLogging() {
        final String LOGGING_CONFIG_FILE_PROPERTY_KEY = "java.util.logging.config.file";
        final String LOGGING_CONFIG_FILE_PATH = Application.class.getClassLoader()
                                                                 .getResource("logging.properties")
                                                                 .getFile();
        System.setProperty(LOGGING_CONFIG_FILE_PROPERTY_KEY, LOGGING_CONFIG_FILE_PATH);
    }
}
