package tech.badprogrammer.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class ContactUtil {

    private static final Logger        LOGGER     = Logger.getLogger(ContactUtil.class.getName());
    private static final AtomicInteger CONTACT_ID = new AtomicInteger(0);
    private static       ContactUtil   INSTANCE   = null;

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
    }

    private void initializeContactId() {
        LOGGER.info("Contact ID initialized to 0.");
        CONTACT_ID.set(0);
    }

    public int generateNewContactId() {
        LOGGER.fine("New Contact ID generated.");
        return CONTACT_ID.incrementAndGet();
    }

    private void destroy() {
        // TODO: Implement clean-up
    }
}
