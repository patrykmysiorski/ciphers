package com.ciphers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.ciphers.Cesar.cesar;
import static com.ciphers.Homophonic.*;

public class Main {
    public static int printWelcome(Scanner scanner) {
        System.out.println("Wybierz szyfr: ");
        System.out.println("1. Szyfr Cezara");
        System.out.println("2. Szyfr homofoniczny");
        System.out.println("3. Szyfr wieloalfebotowy - niestety nie zaimplementowany :(");
        int userInput = scanner.nextInt();
        scanner.nextLine();
        return userInput;
    }

    public static int askUserForDirection(Scanner scanner) {
        System.out.println("Proszę wybrać kierunek szyfru: ");
        System.out.println("1. szyfrowanie");
        System.out.println("2. deszyfrowanie");
        int direction = scanner.nextInt();
        scanner.nextLine();
        return direction;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            int cipherType = printWelcome(scanner);
            int direction = askUserForDirection(scanner);
            switch (cipherType) {
                case 1:
                    System.out.println("Proszę podać przesunięcie: ");
                    int shift = scanner.nextInt();
                    scanner.nextLine();
                    cesar(shift, scanner, direction);
                    break;
                case 2:
                    System.out.println("Proszę podać nazwę pliku zawierającą klucz z rozszerzeniem txt (bez rozszerzenia):");
                    String fileName = scanner.nextLine();
                    ArrayList<ArrayList<String>> keysArray = getKeyListFromFile(fileName);
                    switch (direction) {
                        case 1:
                            homoCipherSaveToArray(keysArray, scanner);
                            break;
                        case 2:
                            reverseHomoCipherFromArray(keysArray, scanner);
                            break;
                        default:
                            System.out.println("Niepoprawny kierunek");
                    }
                    break;
                case 3:
                    switch (direction) {
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            System.out.println("Niepoprawny kierunek");
                    }
                    break;
                default:
                    System.out.println("Niepoprawny numer szyfru");
            }
        }
    }
}
