package com.mycompany.controller;

import com.mycompany.cipher.Caeasar;
import com.mycompany.cipher.ColumnTransposition;
import com.mycompany.cipher.Vigenere;

public class CipherController {

    public CipherController() {
    }

    private String removeLineBreaks(String text) {
        return text.replace("\n", "").replace("\r", "");
    }

    public void handleCypherCall(int cipher, int action) {

        FileController fileController = new FileController();
        String readContent;
        String result = "";
        String key;

        switch (cipher) {
            case 0: // Caeasar Cipher
                Caeasar caeasar = new Caeasar();
                key = caeasar.getKey();
                readContent = fileController.readFile("text");
                readContent = removeLineBreaks(readContent);

                switch (action) {
                    case 0: // Encrypt
                        result = caeasar.encrypt(readContent, Integer.parseInt(key));
                        break;
                    case 1: // Decrypt
                        result = caeasar.decrypt(readContent, Integer.parseInt(key));
                        break;
                    default:
                        throw new AssertionError();
                }

                fileController.writeFile(result, "new");
                break;

            case 1: // Vigenère Cipher
                Vigenere vigenere = new Vigenere();
                readContent = fileController.readFile("text");
                readContent = removeLineBreaks(readContent);
                key = vigenere.getKey(readContent);

                switch (action) {
                    case 0: // Encrypt
                        result = vigenere.encrypt(readContent, key);
                        break;
                    case 1: // Decrypt
                        result = vigenere.decrypt(readContent, key);
                        break;
                    default:
                        throw new AssertionError();
                }

                fileController.writeFile(result, "new");
                break;
                
            case 2:
                ColumnTransposition colTransposition = new ColumnTransposition();
                readContent = fileController.readFile("text");
                readContent = removeLineBreaks(readContent);
                key = colTransposition.getKey();
                
                switch (action) {
                    case 0: // Encrypt
                        result = colTransposition.encrypt(readContent, key);
                        break;
                    case 1: // Decrypt
                        result = colTransposition.decrypt(readContent, key);
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
