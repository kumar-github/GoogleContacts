package tech.badprogrammer.util;

import java.util.concurrent.atomic.AtomicInteger;

public class ContactUtil {

    private static final AtomicInteger CONTACT_ID = new AtomicInteger(0);

    public ContactUtil() {
        init();
    }

    private void init() {
        initializeContactId();
    }

    private void initializeContactId() {
        CONTACT_ID.set(0);
    }

    public int generateNewContactId() {
        return CONTACT_ID.incrementAndGet();
    }

    private void destroy() {
        // TODO: Implement clean-up
    }
}
