package Project.Utils;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class Encode {

    public static String encodeSHA512(String string, String salt) {
        return new ShaPasswordEncoder(512).encodePassword(string, salt);
    }

}
