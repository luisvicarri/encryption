package com.mycompany.cipher;

import java.util.Scanner;

public class Caeasar {
    
    public String getKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the KEY (0-255): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number (0-255): ");
            scanner.next();
        }
        int key = scanner.nextInt();
        return String.valueOf(key);
    }
    
    public String encrypt(String plainText, int key) {
        return replace(plainText, key);
    }
    
    public String decrypt(String cipherText, int key) {
        return replace(cipherText, -key);
    }
    
    private String replace(String text, int key) {
        String result = "";
        int totalASCII = 256;
        
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            c = (char) ((c + key + totalASCII) % 256);
            result += c;
        }
        
        return result;
    }
    
}