package tech.badprogrammer.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AddressUtil {

    private static final Logger        LOGGER     = Logger.getLogger(AddressUtil.class.getName());
    private static final AtomicInteger ADDRESS_ID = new AtomicInteger(0);
    private static       AddressUtil   INSTANCE   = null;

    private AddressUtil() {
        init();
    }

    public static AddressUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddressUtil();
        }
        return INSTANCE;
    }

    private void init() {
        initializeAddressId();
    }

    private void initializeAddressId() {
        LOGGER.info("Address ID initialized to 0.");
        ADDRESS_ID.set(0);
    }

    public int generateNewAddressId() {
        LOGGER.fine("New Address ID generated.");
        return ADDRESS_ID.incrementAndGet();
    }

    private void destroy() {
        // TODO: Implement clean-up
    }
}
