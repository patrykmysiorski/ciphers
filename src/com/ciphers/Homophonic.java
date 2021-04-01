package com.ciphers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static com.ciphers.FileHandlers.readTextFromFile;
import static com.ciphers.FileHandlers.saveToFile;

public class Homophonic {
    public static ArrayList<ArrayList<String>> getKeyListFromFile(String fileName) {
        ArrayList<ArrayList<String>> keysArray = new ArrayList<>();
        try {
            File myObj = new File(fileName + ".txt");
            Scanner fileScanner = new Scanner(myObj);
            while (fileScanner.hasNextLine()) {
                ArrayList<String> valuesForOneLetter = new ArrayList<>();
                String[] values = fileScanner.nextLine().split(",");
                for (String value : values) {
                    valuesForOneLetter.add(value);
                }
                keysArray.add(valuesForOneLetter);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Wystąpił błąd. Spróbuj jeszcze raz");
        }
        return keysArray;
    }

    public static char findLetterForKey(ArrayList<ArrayList<String>> keysArray, String key) {
        int letterIndex = 65;
        for (ArrayList<String> letter : keysArray) {
            for (String letterKey : letter) {
                if (letterKey.equals(key)) {
                    return (char) letterIndex;
                }
            }
            letterIndex++;
        }
        return '?';
    }

    public static void reverseHomoCipher(ArrayList<ArrayList<String>> keysArray, Scanner scanner) {
        final String AWESOME_REGEX_FOR_SPLIT_EVERY_TWO_CHARACTERS = "(?<=\\G.{2})"; // thanks, stackoverflow :)
        String toDecode = readTextFromFile(scanner);
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        String[] encodedWords = toDecode.split(" ");

        for (String singleWord : encodedWords) {
            ArrayList<String> singleWordsByCharacter = new ArrayList<>();
            String[] splitedWord = singleWord.split(AWESOME_REGEX_FOR_SPLIT_EVERY_TWO_CHARACTERS);
            for (String character : splitedWord) {
                singleWordsByCharacter.add(character);
            }
            words.add(singleWordsByCharacter);
        }

        for (ArrayList<String> word : words) {
            for (String character : word) {
                System.out.print(findLetterForKey(keysArray, character));
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void homoCipher(ArrayList<ArrayList<String>> keysArray, Scanner scanner) throws FileNotFoundException {
        String toEncode = readTextFromFile(scanner).toUpperCase();
        String[] encoded = new String[toEncode.length()];

        int index = 0;
        for (char character : toEncode.toCharArray()) {
            int currentLetterAsInt = character;

            if (character == ' ') {
                encoded[index] = " ";
            } else {
                ArrayList<String> keysForCurrentLetter = keysArray.get(currentLetterAsInt - 65);
                String encodedCharacter = getRandom(keysForCurrentLetter);
                encoded[index] = encodedCharacter;
            }
            index++;
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (String character : encoded) {
            stringBuilder.append(character);
        }

        saveToFile(scanner, stringBuilder.toString());
    }

    public static void homoCipherSaveToArray(ArrayList<ArrayList<String>> keysArray, Scanner scanner) throws FileNotFoundException {
        String toEncode = readTextFromFile(scanner).toUpperCase();
        String[] encoded = new String[toEncode.length()];
        int index = 0;
        for (char character : toEncode.toCharArray()) {
            int currentLetterAsInt = character;

            if (character == ' ') {
                encoded[index] = " ";
            } else {
                ArrayList<String> keysForCurrentLetter = keysArray.get(currentLetterAsInt - 65);
                String encodedCharacter = getRandom(keysForCurrentLetter);
                encoded[index] = encodedCharacter;
            }
            index++;
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < encoded.length; i++) {
            if (i < encoded.length - 1) {
                if (encoded[i + 1].equals(" ")) {
                    stringBuilder.append(encoded[i]);
                } else {
                    stringBuilder.append(encoded[i]);
                    if (!encoded[i].equals(" ")) {
                        stringBuilder.append(",");
                    }
                }
            } else {
                stringBuilder.append(encoded[i]);
            }
        }
        saveToFile(scanner, stringBuilder.toString());
    }

    public static void reverseHomoCipherFromArray(ArrayList<ArrayList<String>> keysArray, Scanner scanner) {
        String toDecode = readTextFromFile(scanner);
        ArrayList<ArrayList<String>> words = new ArrayList<>();
        String[] encodedWords = toDecode.split(" ");

        for (String singleWord : encodedWords) {
            ArrayList<String> singleWordsByCharacter = new ArrayList<>();
            String[] splitedWord = singleWord.split(",");
            for (String character : splitedWord) {
                singleWordsByCharacter.add(character);
            }
            words.add(singleWordsByCharacter);
        }

        for (ArrayList<String> word : words) {
            for (String character : word) {
                System.out.print(findLetterForKey(keysArray, character));
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    public static String getRandom(ArrayList<String> array) {
        int random = new Random().nextInt(array.size());
        return array.get(random);
    }
}
