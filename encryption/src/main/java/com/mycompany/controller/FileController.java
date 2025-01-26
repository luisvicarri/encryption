package com.mycompany.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class FileController {

    public String readFile(String target) {
        System.out.println("Please select a " + target + " file to continue.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select file to encrypt/decrypt");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected File: " + selectedFile.getAbsolutePath() + "\n");

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder fileContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append(System.lineSeparator());
                }

                return fileContent.toString();
            } catch (IOException ex) {
                Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("File selection cancelled.");
        }

        return null;

    }

    public void writeFile(String content, String target) {
        System.out.println("Please select a place to save the " + target + " file.");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save the file");

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                writer.write(content);
                System.out.println("File saved successfully at: " + fileToSave.getAbsolutePath() + "\n");
            } catch (IOException e) {
                System.err.println("Error saving the file: " + e.getMessage());
            }
        } else {
            System.out.println("Save operation cancelled by the user.");
        }
    }

}
