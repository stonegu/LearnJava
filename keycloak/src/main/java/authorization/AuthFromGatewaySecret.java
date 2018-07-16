package authorization;

import base64.UsernamePwdEncoding;

public class AuthFromGatewaySecret {
    public static void main(String... args) {
        System.out.println("security header: Basic " + UsernamePwdEncoding.encoding("G3", "9a24a049-969d-4d68-b01f-1ccbb865ba78"));
    }
}
