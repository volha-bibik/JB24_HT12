package com.company.ht11.library.service;

import com.company.ht11.library.entity.*;

import java.io.*;
import java.util.ArrayList;

public class FileService {
    public static void readFile(String fileName, ArrayList fileContext) {
        try {
            InputStream is = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                fileContext.add(line);
                line = null;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            fileContext = null;
        } catch (IOException e) {
            System.out.println("IO error");
            fileContext = null;
        }
    }

    public static void addToLibrary(Library shell, ArrayList<String> fileContext) {
        String[] parts;
        for (String line1 : fileContext) {
            parts = line1.split(", ");
            try {
                switch (parts[0]) {
                    case "Newspaper":
                        shell.add(new Newspaper(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                        break;
                    case "Magazine":
                        shell.add(new Magazine(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Boolean.parseBoolean(parts[5])));
                        break;
                    case "ProgrammingBook":
                        shell.add(new ProgrammingBook(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4], parts[5], Integer.parseInt(parts[6]), parts[7], parts[8]));
                        break;
                    case "ScientificBook":
                        shell.add(new ScientificBook(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4], parts[5], Integer.parseInt(parts[6]), parts[7]));
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Incorrect data provided (some fields are empty), please check it!");
            }

        }
    }
}
