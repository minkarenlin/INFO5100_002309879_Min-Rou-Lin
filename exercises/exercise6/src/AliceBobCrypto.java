import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;

public class AliceBobCrypto {

    public static void main(String[] args) throws Exception {
        // Generate AES key (256 bits)
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey aesKey = keyGen.generateKey();

        // Create Alice and Bob
        User alice = new User("Alice");
        User bob = new User("Bob");

        // AES Symmetric Encryption
        String originalMessage = "Hi Bob, it's Alice!!!";
        System.out.println("=== Symmetric AES Encryption ===");
        System.out.println(alice.getName() + ": I'm sending a secret to " + bob.getName() + " using AES-256.");
        System.out.println("Original message: " + originalMessage);

        byte[] iv = new byte[12]; // GCM recommends 12 bytes IV
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        byte[] encryptedMessage = encryptAES(originalMessage.getBytes(StandardCharsets.UTF_8), aesKey, iv);
        System.out.println(alice.getName() + ": I've encrypted the message with AES and sent it.");

        byte[] decryptedMessage = decryptAES(encryptedMessage, aesKey, iv);
        System.out.println(bob.getName() + ": I received and decrypted the message.");
        System.out.println("Decrypted (AES): " + new String(decryptedMessage, StandardCharsets.UTF_8));


        // RSA Asymmetric Encryption
        System.out.println("\n=== Asymmetric RSA Encryption ===");
        String messageForBob = "This is a secret from Alice.";
        System.out.println(alice.getName() + ": Now I'll send another secret to " + bob.getName() + " using his RSA public key.");
        byte[] encryptedWithRSA = encryptRSA(messageForBob.getBytes(), bob.getPublicKey());
        System.out.println(alice.getName() + ": Message encrypted with " + bob.getName() + "'s public key.");

        byte[] decryptedWithRSA = decryptRSA(encryptedWithRSA, bob.getPrivateKey());
        System.out.println(bob.getName() + ": I used my private key to decrypt it.");
        System.out.println("Decrypted (RSA): " + new String(decryptedWithRSA));


        // RSA Digital Signature
        System.out.println("\n=== Digital Signature ===");
        String messageToSign = "This message is from Alice.";
        System.out.println(alice.getName() + ": I will sign this message to prove it's really from me.");
        byte[] signature = signMessage(messageToSign.getBytes(), alice.getPrivateKey());
        System.out.println(alice.getName() + ": Message signed with my private key.");

        boolean isVerified = verifySignature(messageToSign.getBytes(), signature, alice.getPublicKey());
        System.out.println(bob.getName() + ": I verified the signature using " + alice.getName() + "'s public key.");
        System.out.println("Signature verified: " + isVerified);
    }

    static byte[] encryptAES(byte[] plaintext, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        return cipher.doFinal(plaintext);
    }

    static byte[] decryptAES(byte[] ciphertext, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(ciphertext);
    }

    static byte[] encryptRSA(byte[] plaintext, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    static byte[] decryptRSA(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(ciphertext);
    }

    static byte[] signMessage(byte[] message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    static boolean verifySignature(byte[] message, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signatureBytes);
    }

    static class User {
        private final String name;
        private final KeyPair keyPair;

        public User(String name) throws NoSuchAlgorithmException {
            this.name = name;
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            this.keyPair = generator.generateKeyPair();
        }

        public PublicKey getPublicKey() {
            return keyPair.getPublic();
        }

        public PrivateKey getPrivateKey() {
            return keyPair.getPrivate();
        }

        public String getName() {
            return name;
        }
    }
}
