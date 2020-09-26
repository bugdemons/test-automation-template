package crypto;

import java.util.Scanner;

public class PasswordEncoder {
    public static void main(String[] args) {
        String passToEncrypt = getPasswordToEncode();
        String encryptedPassword = AES.encrypt(passToEncrypt);
        System.out.printf("Encrypted password: %s", encryptedPassword);
    }

    private static String getPasswordToEncode() {
        System.out.print("Enter password to encode: ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
