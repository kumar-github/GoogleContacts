package tech.badprogrammer;

import org.apache.commons.lang3.tuple.ImmutablePair;
import tech.badprogrammer.model.Address;
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
        john.setPhone(ImmutablePair.of("+910000000000", "Work"));
        john.setLabel("Work");
        john.setEmail(ImmutablePair.of("john@doe.com", "Personal"));
        john.setWebsite("www.johndoe.com");
        john.setNotes("This is a note");
        john.setProfilePicture("/Users/kumar/delete-today/john-profile-pic.jpg");
        john.setBirthday("03-25-1980");
        john.setSignificantDate("03-25-1980");
        john.setCompany("Amphora");
        john.setJobTitle("Software Engineer");
        john.setCustomField("Some custom value");

        final Address johnAddress = new Address();
        johnAddress.setStreet("John's Street");
        johnAddress.setCity("John's City");
        johnAddress.setZipcode("101010");
        johnAddress.setState("John's State");
        johnAddress.setCountry("John's Country");
        john.setAddress(ImmutablePair.of(johnAddress, "Home"));

        result = contactManager.addContact(john);

        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());
        LOGGER.info(result.getSignificantDate());
        LOGGER.info(result.getProcessedSignificantDate()
                          .toString());

        final Contact marius = new Contact();
        marius.setFirstName("Marius");
        marius.setLastName("Filip");
        marius.setPhone(ImmutablePair.of("+911111111111", "Mobile"));
        marius.setLabel("Friends");
        marius.setEmail(ImmutablePair.of("marius@filip.com", "Work"));
        marius.setWebsite("www.mariusfilip.com");
        marius.setNotes("This is a note");
        marius.setProfilePicture("/Users/kumar/delete-today/marius-profile-pic.jpg");
        marius.setBirthday("04-15-1970");
        marius.setSignificantDate("04-15-1970");
        marius.setCompany("HCL");
        marius.setJobTitle("Senior Software Engineer");
        marius.setCustomField("Some other custom value");

        final Address mariusAddress = new Address();
        mariusAddress.setStreet("Marius's Street");
        mariusAddress.setCity("Marius's City");
        mariusAddress.setZipcode("202020");
        mariusAddress.setState("Marius's State");
        mariusAddress.setCountry("Marius's Country");
        marius.setAddress(ImmutablePair.of(mariusAddress, "Work"));

        result = contactManager.addContact(marius);

        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());
        LOGGER.info(result.getSignificantDate());
        LOGGER.info(result.getProcessedSignificantDate()
                          .toString());

        final Contact david = new Contact();
        david.setFirstName("David");
        david.setLastName("Allen");
        david.setPhone(ImmutablePair.of("+912222222222", null));
        david.setLabel("Gym");
        david.setEmail(ImmutablePair.of("david@allen.com", "Work"));
        david.setWebsite("www.davidallen.com");
        david.setNotes("This is a note");
        david.setProfilePicture("/Users/kumar/delete-today/david-profile-pic.jpeg");
        david.setBirthday("12-31");
        david.setSignificantDate("12-31");
        david.setCompany("Microsoft");
        david.setJobTitle("Senior Technical Architect");
        david.setCustomField("Nothing");

        result = contactManager.addContact(david);

        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());
        LOGGER.info(result.getSignificantDate());
        LOGGER.info(result.getProcessedSignificantDate()
                          .toString());

        final Contact chris = new Contact();
        chris.setFirstName("Chris");
        chris.setLastName("Wayne");
        chris.setPhone(ImmutablePair.of("+913333333333", null));
        chris.setLabel("Work");
        chris.setEmail(ImmutablePair.of("chris@wayne.com", null));
        chris.setBirthday("01-29");
        chris.setSignificantDate("01-29");
        result = contactManager.addContact(chris);
        LOGGER.info(result.toString());
        LOGGER.info(result.getBirthday());
        LOGGER.info(result.getProcessedBirthday()
                          .toString());
        LOGGER.info(result.getSignificantDate());
        LOGGER.info(result.getProcessedSignificantDate()
                          .toString());

        final Contact bruce = new Contact();
        bruce.setFirstName("Bruce");
        bruce.setLastName("Wayne");
        bruce.setPhone(ImmutablePair.of("+914444444444", ""));
        bruce.setEmail(ImmutablePair.of("bruce@wayne.com", ""));
        bruce.setLabel("Gym");
        LOGGER.info(contactManager.addContact(bruce)
                                  .toString());

        // should have 5 contacts
        LOGGER.info(contactManager.getAllContacts()
                                  .toString());

        chris.setLastName("Mudry");
        chris.setPhone(ImmutablePair.of("+915555555555", ""));
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
