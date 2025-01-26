package com.mycompany.cipher;

import java.util.Scanner;

public class Vigenere {

    public String getKey(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the KEY: ");
        String key = scanner.nextLine();
        key = adjustKey(key, text);
        return key;
    }

    private String adjustKey(String key, String text) {
        StringBuilder adjustedKey = new StringBuilder();
        int textLength = text.length();
        int keyLength = key.length();
        
        for (int i = 0; i < textLength; i++) {
            adjustedKey.append(key.charAt(i % keyLength));
        }

        return adjustedKey.toString();
    }

    public String encrypt(String plainText, String key) {
        return replace(plainText, key, true);
    }

    public String decrypt(String cipherText, String key) {
        return replace(cipherText, key, false);
    }

    private String replace(String text, String key, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();
        int totalASCII = 256;

        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);
            int shift = isEncrypt ? keyChar : -keyChar;

            textChar = (char) ((textChar + shift + totalASCII) % 256);
            result.append(textChar);

        }

        return result.toString();
    }
}
