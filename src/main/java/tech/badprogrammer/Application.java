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
        Contact              result;

        // should be empty
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        final Contact john = new Contact();
        john.setFirstName("John");
        john.setLastName("Doe");
        john.setPhone("+910000000000");
        john.setLabel("Work");
        john.setEmail("john@doe.com");
        john.setWebsite("www.johndoe.com");
        john.setNotes("This is a note");
        john.setProfilePicture("/Users/kumar/delete-today/john-profile-pic.jpg");
        john.setBirthday("03-25-1980");
        result = contactManager.addContact(john);
        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());

        final Contact marius = new Contact();
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone("+911111111111");
        marius.setLabel("Friends");
        marius.setEmail("marius@filip.com");
        marius.setWebsite("www.mariusfilip.com");
        marius.setNotes("This is a note");
        marius.setProfilePicture("/Users/kumar/delete-today/marius-profile-pic.jpg");
        marius.setBirthday("04-15-1970");
        result = contactManager.addContact(marius);
        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());

        final Contact david = new Contact();
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone("+912222222222");
        david.setLabel("Gym");
        david.setEmail("david@allen.com");
        david.setWebsite("www.davidallen.com");
        david.setNotes("This is a note");
        david.setProfilePicture("/Users/kumar/delete-today/david-profile-pic.jpeg");
        david.setBirthday("12-31");
        result = contactManager.addContact(david);
        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());

        final Contact chris = new Contact();
        chris.setFirstName("Chris");
        chris.setLastName("Wayne");
        chris.setPhone("+913333333333");
        chris.setLabel("Work");
        chris.setBirthday("01-29");
        result = contactManager.addContact(chris);
        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
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
