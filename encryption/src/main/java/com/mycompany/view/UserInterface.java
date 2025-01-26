package com.mycompany.view;

import java.util.Scanner;

public class UserInterface {

    public UserInterface() {
    }

    public void printMenu(int state) {
        String text = "";

        switch (state) {
            case 0:
                text = """
                       Welcome to the Encryption Program!
                       
                       Please choose an action:
                         0. Encrypt;
                         1. Decrypt;
                         2. Exit.
                       """;
                break;
            case 1:
                text = """
                       Please choose a cipher:
                         0. Caesar Cipher;
                         1. Vigenere Cipher;
                         2. Column Transposition;
                         3. Exit.
                       """;
                break;
            case 2:
                text = """
                       Please choose an algorithm:
                         0. Symmetric encryption (AES - Advanced Encryption Standard);
                         1. Asymmetric encryption (RSA - Rivest–Shamir–Adleman);
                         2. Exit.
                       """;
                break;
            default:
                throw new AssertionError();
        }

        System.out.println(text);
    }

    public int getUserChoice(int state) {
        Scanner scanner = new Scanner(System.in);
        String text = "";

        switch (state) {
            case 0, 2:
                text = "0-2";
                break;
            case 1:
                text = "0-3";
                break;
            default:
                throw new AssertionError();
        }

        System.out.print("Enter your choice (" + text + "): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number (" + text + "): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        return choice;
    }

    public void handleChoice(int choice, int state) {
        switch (state) {
            case 0:
                switch (choice) {
                    case 0:
                        System.out.println("You selected encrypt.\n");
                        break;
                    case 1:
                        System.out.println("You selected decrypt.\n");
                        break;
                    case 2:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please restart the program and choose a valid option.");
                }
                break;
            case 1:
                switch (choice) {
                    case 0:
                        System.out.println("You selected Caesar Cipher.\n");
                        break;
                    case 1:
                        System.out.println("You selected Vigenere Cipher.\n");
                        break;
                    case 2:
                        System.out.println("You selected Column Transposition.\n");
                        break;
                    case 3:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please restart the program and choose a valid option.");
                }
                break;
            case 2:
                switch (choice) {
                    case 0:
                        System.out.println("You selected symmetric encryption.\n");
                        break;
                    case 1:
                        System.out.println("You selected asymmetric encryption.\n");
                        break;
                    case 2:
                        System.out.println("Exiting the program. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please restart the program and choose a valid option.");
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
