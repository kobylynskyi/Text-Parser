package com.epam.bogdankobylinsky.textparser;

import com.epam.bogdankobylinsky.textparser.textparts.Paragraph;
import com.epam.bogdankobylinsky.textparser.textparts.Sentence;
import com.epam.bogdankobylinsky.textparser.textparts.Text;
import com.epam.bogdankobylinsky.textparser.textparts.Word;
import java.util.HashMap;

/**
 * Contains the printing utilities: printing special messages,
 * text,sentences,words and others on the console.
 *
 * @author Bogdan Kobylinsky.
 */
public class Printer {

    /**
     * Printing the welcome message.
     */
    public static void printWelcome() {
        print("\nWelcome to the Text Parser! \n");
    }

    /**
     * Printing the message if error occurs while reading the file.
     */
    public static void printIOException() {
        print("\nAn error occured while reading the file!\n"
                + "To parse the file: \n"
                + "1) Place it in the CLASSPATH directory. \n"
                + "2) Name it \"" + Scanner.FILE_NAME + "\" \n\n"
                + "Press 'Enter' to start parsing.");
    }

    /**
     * Printing all sentences of the text and their serial number.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     */
    public static void printSeparateSentences(Text text) {
        if (text == null) {
            print("\nYou must to parse the file before printing the sentences!");
            return;
        }
        print("\nAll sentences in text: ");
        int count = 0;
        for (Paragraph paragraph : text.getParagraphs()) {
            if (paragraph.isCode()) {
                continue;
            }
            for (Sentence sentence : paragraph.getSentences()) {
                System.out.println(count++ + ". " + sentence.getText());
            }
        }
    }

    /**
     * Printing all words in all sentences if the text.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object.
     * @param max maximal amount of words to print on the console.
     */
    public static void printAllWords(Text text, int max) {
        if (text == null) {
            print("\nYou must to parse the file before printing the words!");
            return;
        }
        print("\nAll words in text: ");
        HashMap<Word, Integer> words = Utils.getAllWordsSortedByPopularity(text);
        int count = 0;
        for (Word word : words.keySet()) {
            if (count > max) {
                break;
            }
            System.out.println("[" + words.get(word) + "] \t" + word.getText());
            count++;
        }
    }

    /**
     * Printing the set of words in the single sentence.
     *
     * @param words the HashMap which has Word object as key and Integer as
     * value.
     */
    public static void printUniqueWords(HashMap<Word, Integer> words) {
        if (words == null) {
            print("\nYou must to parse the file before printing the words!");
            return;
        }
        print("\nWords in sentence sorted by the uniqueness in the text: ");
        for (Word word : words.keySet()) {
            print("\"" + word.getText() + "\" [repeats " + (words.get(word)) + " times]");
        }
    }

    /**
     * Printing the menu.
     */
    public static void printMenu() {
        print("\n1 - Scan and parse file (CLASSPATH\\" + Scanner.FILE_NAME + ").\n"
                + "2 - Show separate sentences.\n"
                + "3 - Show all words sorted by frequency.\n"
                + "4 - Find a word in the first sentence, which is not present in any of other sentences.\n"
                + "5 - Assemble text from the parts.\n"
                + "0 - Exit.");
    }

    /**
     * Printing the special message on the console.
     *
     * @param text th text of message to print.
     */
    public static void print(String text) {
        System.out.println(text);
    }
}