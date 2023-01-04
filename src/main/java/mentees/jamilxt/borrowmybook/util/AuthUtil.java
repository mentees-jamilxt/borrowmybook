package mentees.jamilxt.borrowmybook.util;

import org.apache.commons.lang3.RandomStringUtils;

public class AuthUtil {
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
