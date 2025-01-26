package com.mycompany.controller;

import com.mycompany.algorithm.asymmetric.RSA;
import com.mycompany.algorithm.symmetric.AES;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AlgorithmController {

    private String removeLineBreaks(String text) {
        return text.replace("\n", "").replace("\r", "");
    }

    private boolean isValidBase64(String s) {
        return s.matches("^[A-Za-z0-9+/=]+$");
    }

    public void handleAlgCall(int algorithm, int action) {
        FileController fileController = new FileController();
        String readContent;
        String result = "";

        switch (algorithm) {
            case 0: // Symmetric encryption - AES
                AES aes = new AES();
                readContent = fileController.readFile("text");
                readContent = removeLineBreaks(readContent);
                SecretKey key;
                IvParameterSpec iv;

                switch (action) {
                    case 0: // Encrypt
                        key = aes.getKey();
                        iv = aes.getIv();

                        fileController.writeFile(Base64.getEncoder().encodeToString(key.getEncoded()), "KEY");

                        fileController.writeFile(Base64.getEncoder().encodeToString(iv.getIV()), "IV");

                        result = aes.encrypt(readContent, key, iv);
                        break;

                    case 1: // Decrypt
                        String keyString = fileController.readFile("KEY").trim();

                        String ivString = fileController.readFile("IV").trim();

                        if (!isValidBase64(keyString) || !isValidBase64(ivString)) {
                            throw new IllegalArgumentException("Invalid Base64 content in key or IV file.");
                        }

                        byte[] keyBytes = Base64.getDecoder().decode(keyString);
                        key = new SecretKeySpec(keyBytes, "AES");

                        byte[] ivBytes = Base64.getDecoder().decode(ivString);
                        iv = new IvParameterSpec(ivBytes);

                        result = aes.decrypt(readContent, key, iv);
                        break;
                    default:
                        throw new AssertionError();
                }

                fileController.writeFile(result, "new");
                break;

            case 1: // Asymmetric encryption - RSA
                RSA rsa = new RSA();
                readContent = fileController.readFile("text");
                readContent = removeLineBreaks(readContent);
                String publicKey;
                String privateKey;

                switch (action) {
                    case 0: // Encrypt
                        rsa.getKeyPair();
                        publicKey = rsa.getPublicKey();
                        privateKey = rsa.getPrivateKey();
                        
                        fileController.writeFile(publicKey, "Public Key");
                        fileController.writeFile(privateKey, "Private Key");

                        result = rsa.encrypt(readContent, publicKey);
                        break;

                    case 1: // Decrypt
                        publicKey = fileController.readFile("Public Key").trim();
                        privateKey = fileController.readFile("Private Key").trim();
                        
                        if (!isValidBase64(publicKey) || !isValidBase64(privateKey)) {
                            throw new IllegalArgumentException("Invalid Base64 content in key file.");
                        }
                        
                        result = rsa.decrypt(readContent, privateKey);
                        break;
                    default:
                        throw new AssertionError();
                }

                fileController.writeFile(result, "new");
                break;
            default:
                throw new AssertionError();
        }
    }

}
