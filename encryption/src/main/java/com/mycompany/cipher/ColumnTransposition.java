package com.mycompany.cipher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ColumnTransposition {

    public String getKey() {
        Scanner scanner = new Scanner(System.in);
        String key;

        while (true) {
            System.out.print("Please enter the KEY: ");
            key = scanner.nextLine().toUpperCase();

            if (hasDuplicateCharacters(key)) {
                System.out.println("Error: The key contains duplicate letters. Please enter a valid key.");
            } else {
                break;
            }
        }

        return key;
    }

    private boolean hasDuplicateCharacters(String key) {
        HashSet<Character> seen = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (seen.contains(c)) {
                return true;
            }
            seen.add(c);
        }
        return false;
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.replaceAll("\\s", "").toLowerCase();
        int cols = key.length();
        int rows = (int) Math.ceil((double) plainText.length() / cols);

        while (plainText.length() < rows * cols) {
            plainText += 'x';
        }

        List<Integer> keyOrder = getKeyOrder(key);

        StringBuilder cipherText = new StringBuilder();
        int j = 0;
        int lineLimit = key.length();
        int shift = 0;
        int index;

        for (int i = 0; i < rows; i++) {

            while (j < lineLimit) {
                index = (keyOrder.get(j % key.length())) + shift;
                cipherText.append(plainText.charAt(index));
                j++;
            }

            lineLimit += key.length();
            shift += key.length();
        }

        return cipherText.toString();
    }

    private List<Integer> getKeyOrder(String key) {
        List<Character> keyChars = new ArrayList<>();
        for (char c : key.toCharArray()) {
            keyChars.add(c);
        }

        List<Character> sortedKeyChars = new ArrayList<>(keyChars);
        Collections.sort(sortedKeyChars);

        List<Integer> keyOrder = new ArrayList<>();
        for (char c : sortedKeyChars) {
            keyOrder.add(keyChars.indexOf(c));
            keyChars.set(keyChars.indexOf(c), null); // Evitar duplicados
        }

        return keyOrder;
    }

    public String decrypt(String cipherText, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) cipherText.length() / cols);

        List<Integer> keyOrder = getKeyOrder(key);

        StringBuilder plainText = new StringBuilder();
        int j = 0;
        int lineLimit = key.length();
        int shift = 0;
        int index;

        for (int i = 0; i < rows; i++) {

            while (j < lineLimit) {
                index = (keyOrder.indexOf(j % key.length())) + shift;
                plainText.append(cipherText.charAt(index));
                j++;
            }

            lineLimit += key.length();
            shift += key.length();
        }

        return plainText.toString();

    }

}
