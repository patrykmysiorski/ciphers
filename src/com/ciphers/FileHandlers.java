package com.ciphers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandlers {
    public static String readTextFromFile(Scanner scanner) {
        String text = "";
        try {
            System.out.println("Proszę podać nazwę pliku z tekstem do odczytania: (bez rozszerzenia)");
            String fileName = scanner.nextLine();

            File myObj = new File(fileName + ".txt");
            Scanner fileScanner = new Scanner(myObj);
            while (fileScanner.hasNextLine()) {
                text = fileScanner.nextLine();
            }
            fileScanner.close();
            return text;
        } catch (FileNotFoundException e) {
            System.out.println("Wystąpił błąd");
        }
        return "";
    }

    public static void saveToFile(Scanner scanner, String cipheredText) throws FileNotFoundException {
        System.out.println("Proszę podać nazwę pliku do zapisu zaszyfrowanego tesktu: (bez rozszerzenia)");
        String fileName = scanner.nextLine();
        PrintWriter out = new PrintWriter(fileName + ".txt");
        out.println(cipheredText);
        out.close();
    }
}
