package com.ciphers;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.ciphers.FileHandlers.readTextFromFile;
import static com.ciphers.FileHandlers.saveToFile;

public class Cesar {
    public static void cesar(Integer shift, Scanner scanner, int direction) throws FileNotFoundException {
        final Integer ASCII_CODE_FIRST_LETTER = ((int) 'A');
        final Integer ASCII_CODE_LAST_LETTER = ((int) 'Z');
        final Integer ASCII_CODE_SPACE = ((int) ' ');
        final Integer NUMBER_OF_LETTERS = 26;

        String word = readTextFromFile(scanner).toUpperCase();

        final Integer LENGTH_OF_WORD = word.length();

        int[] intCharactersArray = new int[LENGTH_OF_WORD];

        int correctShift = shift % NUMBER_OF_LETTERS;

        if (direction == 1) {
            for (int i = 0; i < word.length(); i++) {
                int charValue = word.charAt(i);
                if (charValue == ASCII_CODE_SPACE) {
                    intCharactersArray[i] = charValue;
                } else {
                    if (charValue + correctShift > ASCII_CODE_LAST_LETTER) {
                        int newValue = (ASCII_CODE_FIRST_LETTER - 1) + (correctShift - (ASCII_CODE_LAST_LETTER - charValue));
                        intCharactersArray[i] = newValue;
                    } else {
                        intCharactersArray[i] = charValue + correctShift;
                    }
                }
            }
        } else {
            for (int i = 0; i < word.length(); i++) {
                int charValue = word.charAt(i);
                if (charValue == ASCII_CODE_SPACE) {
                    intCharactersArray[i] = charValue;
                } else {
                    if (charValue - correctShift < ASCII_CODE_FIRST_LETTER) {
                        intCharactersArray[i] = (ASCII_CODE_LAST_LETTER + 1) - (correctShift + (ASCII_CODE_FIRST_LETTER - charValue));
                    } else {
                        intCharactersArray[i] = charValue - correctShift;
                    }
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int value : intCharactersArray) {
            stringBuilder.append((char) value);
        }

        if (direction == 1) {
            saveToFile(scanner, stringBuilder.toString());
        } else {
            System.out.println(stringBuilder.toString());
        }
    }
}
