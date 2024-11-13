package co.sofka.encryption;

import javax.crypto.SecretKey;

public interface KeyPort {
    String encodeKey(SecretKey key);
    SecretKey decodeKey(String encodedKey);
}
