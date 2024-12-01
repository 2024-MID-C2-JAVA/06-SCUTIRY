package com.example.banco_hex_yoder.encriptacion;

import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncripcionService {
    private static final String ALGORITMO = "AES/CBC/PKCS5PADDING";

    public String encriptar(String textoClaro, String symmetricKey, String initializationVector) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(symmetricKey.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

        byte[] encriptado = cipher.doFinal(textoClaro.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encriptado);
    }

    public String desencriptar(String textoEncriptado, String symmetricKey, String initializationVector) throws Exception {
        IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes("UTF-8"));
        SecretKeySpec keySpec = new SecretKeySpec(symmetricKey.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);

        byte[] decodificado = Base64.getDecoder().decode(textoEncriptado);
        byte[] desencriptado = cipher.doFinal(decodificado);
        return new String(desencriptado, "UTF-8");
    }
}
