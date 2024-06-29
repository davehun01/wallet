package com.example.dave.wallet.Tools;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Printer {

    public void createFile() {
        try {
            File myObj = new File("output.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeToFile(StringBuilder content) {
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(String.valueOf(content));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
