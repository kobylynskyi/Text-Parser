package com.epam.bogdankobylinsky.textparser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Contains scanning utilities: reading the file and the number from console.
 *
 * @author Bogdan
 */
public class Scanner {

    /**
     * Initializing the static scanner of console for input.
     */
    private static java.util.Scanner scanner = new java.util.Scanner(System.in);

    /**
     * Reading the file content.
     *
     * @return the String which contains the text of file.
     * @throws IOException if error occurs while reading the file.
     */
    public static String readFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), "UTF-8"));
        String ls = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append(ls);
        }
        return stringBuilder.toString();
    }

    /**
     * Getting the number from console input.
     *
     * @return the Integer number that user inputted in console. If it is not
     * decimal integer, function returns {@code null}.
     */
    public static Integer getNumber(String query) {
        System.out.print(query);
        String enteredString = scanner.nextLine();
        Integer enteredNumber;
        try {
            enteredNumber = Integer.parseInt(enteredString);
        } catch (NumberFormatException nfe) {
            System.out.println("Incorrect entry! You must enter an integer value.");
            return null;
        }
        return enteredNumber;
    }

    /**
     * Reading the integer number from console. Scanning the console while user
     * enters the value not from the defined set.
     *
     * @param query is the query message.
     * @param variants is the set of numbers, from which user must choose.
     * @return
     */
    public static Integer readIntegerFromSet(String query, Integer... variants) {
        Integer choosedInMenu = null;
        while (choosedInMenu == null || !Arrays.asList(variants).contains(choosedInMenu)) {
            choosedInMenu = Scanner.getNumber(query);
        }
        return choosedInMenu;
    }
    /**
     * The name of file to scan.
     */
    public static final String FILE_NAME = "for_parse.txt";
}