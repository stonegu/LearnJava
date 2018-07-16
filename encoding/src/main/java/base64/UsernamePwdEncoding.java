package base64;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class UsernamePwdEncoding {
    public static void main(String ... args) {
        System.out.println(
                "encoded: " + encoding("username", "password")
        );
    }

    public static String encoding(String username, String password) {
        return Base64.encodeBase64String((username + ':' + password).getBytes(StandardCharsets.UTF_8));
    }
}
