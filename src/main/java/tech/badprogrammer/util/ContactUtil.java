package tech.badprogrammer.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ContactUtil {

    private static final Logger            LOGGER              = Logger.getLogger(ContactUtil.class.getName());
    private static final int               ZERO                = 0;
    private static final AtomicInteger     CONTACT_ID          = new AtomicInteger(0);
    private static       DateTimeFormatter DATE_TIME_FORMATTER = null;

    private static ContactUtil INSTANCE = null;

    private ContactUtil() {
        init();
    }

    public static ContactUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ContactUtil();
        }
        return INSTANCE;
    }

    private void init() {
        initializeContactId();
        initializeDateTimeFormatter();
    }

    private void initializeContactId() {
        LOGGER.info("Contact ID initialized to 0.");
        CONTACT_ID.set(0);
    }

    private void initializeDateTimeFormatter() {
        DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("[MM-dd-uuuu][MM-dd]")
                                                            .parseDefaulting(ChronoField.YEAR, ZERO)
                                                            .toFormatter();
    }

    public int generateNewContactId() {
        LOGGER.fine("New Contact ID generated.");
        return CONTACT_ID.incrementAndGet();
    }

    public DateTimeFormatter dateWithOrWithoutYearFormatter() {
        return DATE_TIME_FORMATTER;
    }

    private void destroy() {
        // TODO: Implement clean-up
    }
}
