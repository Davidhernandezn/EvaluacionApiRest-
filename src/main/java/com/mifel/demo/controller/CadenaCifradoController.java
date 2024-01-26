package com.mifel.demo.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mifel.demo.model.CadenaCifrado;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Arrays;

@RestController
@SpringBootApplication
public class CadenaCifradoController {
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
	
    @RequestMapping(value = "/cifrado/{cadena}")
    public String getCadena(@PathVariable("cadena") String valor) {
        CadenaCifrado cadena = new CadenaCifrado();
        cadena.setCadena(valor);
        String cadenaIngresada = cadena.getCadena();
        System.out.println(cadenaIngresada);      
        
        try {
            // Ejemplo de uso
            String dataToEncrypt = "Hola, este es un mensaje cifrado";
            String encryptionKey = cadenaIngresada; // La llave puede tener cualquier longitud
            String initializationVector = "1234567890123456"; // El vector de inicialización 
            String encryptedData = encrypt(dataToEncrypt, encryptionKey, initializationVector);
            System.out.println("Texto cifrado: " + encryptedData);
            return "Texto cifrado: " + encryptedData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No ingresaste nada";
    }
    
    // Método para cifrar una cadena
    public static String encrypt(String data, String key, String iv) throws Exception {
        // Obtener una clave de 32 bytes a partir de la clave de entrada
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = sha.digest(key.getBytes(StandardCharsets.UTF_8));
        
        // Usar los primeros 16, 24 o 32 bytes para la clave
        int keySize = 16 + ((keyBytes.length - 16) / 8) * 8; // 16, 24 o 32 bytes
        keyBytes = Arrays.copyOf(keyBytes, keySize);
        
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
