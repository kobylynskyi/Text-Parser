package com.epam.bogdankobylinsky.textparser;

import com.epam.bogdankobylinsky.textparser.textparts.Text;
import java.io.IOException;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Printer.printWelcome();

        String textForParsing = null;
        Text text = null;
        boolean repeatMenu = true;
        do {
            Printer.printMenu();
            // Scanning the user input:
            switch (Scanner.readIntegerFromSet("Choose what you want: ", 1, 2, 3, 4, 5, 0)) {
                case 1:
                    // Scanning the file and disassembling it:
                    Printer.print("Scanning the file...");
                    try {
                        textForParsing = Scanner.readFile().trim().replaceAll("\t", " ").replaceAll(" +", " ");
                    } catch (IOException ex) {
                        Printer.printIOException();
                    }
                    text = new Text(textForParsing);
                    Printer.print("Parsing the text...");
                    Parser.parseText(text);
                    Printer.print("DONE!");
                    break;
                case 2:
                    // Printing all sentences of the text:
                    Printer.printSeparateSentences(text);
                    break;
                case 3:
                    // Printing all words of the text, sorted by uniqueness:
                    Printer.printAllWords(text, Scanner.getNumber("Enter the number of words to out: "));
                    break;
                case 4:
                    // Printing words of first sentence, sorted by uniqueness:
                    Printer.printUniqueWords(Utils.findUniqueWord(text.getParagraphs().get(0).getSentences().get(0), text));
                    break;
                case 5:
                    // Assembling text from small parts and printing it on the console:
                    Printer.print(Utils.assembleText(text));
                    break;
                case 0:
                    // Breaking the loop:
                    repeatMenu = false;
                    break;
            }
        } while (repeatMenu);
    }
}
