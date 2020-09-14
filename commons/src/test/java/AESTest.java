import crypto.AES;
import org.testng.annotations.Test;

import java.util.IllformedLocaleException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AESTest {

    private final String PASS_TO_ENCRYPT = "Demo@Password!@#123";
    private final String PASS_TO_DECRYPT = "rGmS+W5To9agFb+ivrukStWbw6PVz7yYPqDLb4C6foU=";

    @Test
    public void encryptionTest() {
        String encryptedPassword = AES.encrypt(PASS_TO_ENCRYPT);
        assertThat(encryptedPassword).isEqualTo(PASS_TO_DECRYPT);
    }

    @Test
    public void emptyPasswordEncryptionTest() {
        try {
            String encryptedPassword = AES.encrypt("");
        } catch (IllegalStateException e) {
            assertThat(e).hasMessage("String to encrypt cannot be empty!");
        }
    }

    @Test
    public void decryptionTest() {
        String decryptedPassword = AES.decrypt(PASS_TO_DECRYPT);
        assertThat(decryptedPassword).isEqualTo(PASS_TO_ENCRYPT);
    }

    @Test
    public void emptyPasswordDecryptionTest() {
        try {
            String decryptedPassword = AES.decrypt("");
        } catch (IllegalStateException e) {
            assertThat(e).hasMessage("String to decrypt cannot be empty!");
        }
    }
}
