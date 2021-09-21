package pl.bugdemons.utils.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static final String MY_KEY = "someRando!%@mKey1Q32";
    private static final String CIPHER_NAME = "AES/ECB/PKCS5Padding";
    private static SecretKeySpec secretKeySpec;

    static {
        setKey(MY_KEY);
    }

    private AES() {
    }

    public static String encrypt(final String stringToEncrypt) {
        if (stringToEncrypt.isEmpty()) {
            throw new IllegalStateException("String to encrypt cannot be empty!");
        }
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encoded = cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(final String stringToDecrypt) {
        if (stringToDecrypt.isEmpty()) {
            throw new IllegalStateException("String to decrypt cannot be empty!");
        }
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decodedFromBase64 = Base64.getDecoder().decode(stringToDecrypt);
            return new String(cipher.doFinal(decodedFromBase64));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setKey(final String myKey) {
        MessageDigest messageDigest;
        byte[] key;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            messageDigest = MessageDigest.getInstance("SHA-1");
            key = messageDigest.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
