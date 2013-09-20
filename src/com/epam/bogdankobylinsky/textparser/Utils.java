package com.epam.bogdankobylinsky.textparser;

import com.epam.bogdankobylinsky.textparser.textparts.Paragraph;
import com.epam.bogdankobylinsky.textparser.textparts.Sentence;
import com.epam.bogdankobylinsky.textparser.textparts.Text;
import com.epam.bogdankobylinsky.textparser.textparts.TextPart;
import com.epam.bogdankobylinsky.textparser.textparts.Word;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Contains the utilities for assembling the text from the parts and finding the
 * unique words in the sentence.
 *
 * @author Bogdan Kobylinsky.
 */
public class Utils {

    /**
     * Assembling the text from separate parts.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     * @return the String of assembled text.
     */
    public static String assembleText(Text text) {
        if (text == null) {
            System.out.println("\nYou must to parse the file before assemble the text!");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Paragraph paragraph : text.getParagraphs()) {
            if (!paragraph.isCode()) {
                for (Sentence sentence : paragraph.getSentences()) {
                    for (TextPart sentencePart : sentence.getWordsAndPunctuations()) {
                        sb.append(sentencePart.getText());
                    }
                }
                sb.append(System.getProperty("line.separator"));
            }
        }
        return sb.toString();
    }

    /**
     * Finding the words in defined sentence and sorting them by uniqueness in
     * the text.
     *
     * @param sentence is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Sentence} object.
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object.
     * @return the HashMap containing the words in defined sentence, sorted by
     * uniqueness of the text.
     */
    public static HashMap<Word, Integer> findUniqueWord(Sentence sentence, Text text) {
        if (text == null) {
            return null;
        }
        HashMap<Word, Integer> wordsInSentence = new HashMap<>();
        for (TextPart sentencePart : sentence.getWordsAndPunctuations()) {
            if (sentencePart instanceof Word) {
                wordsInSentence.put((Word) sentencePart, text.getAllWords().get((Word) sentencePart));
            }
        }

        //Creating new list from values of Map defined above.
        List list = new LinkedList(wordsInSentence.entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        // Adding keys to the values defined above.
        HashMap newMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            newMap.put(entry.getKey(), entry.getValue());
        }
        return newMap;
    }

    /**
     * Getting all Word objects of the text.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object.
     * @return the HashMap of all words of the text.
     */
    public static HashMap<Word, Integer> getAllWordsSortedByPopularity(Text text) {
        List list = new LinkedList(text.getAllWords().entrySet());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return -((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     * Checking the paragraph if it is the code block.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object.
     */
    public static void scanParagraphsForCode(Text text) {
        boolean codeStarted = false;
        int openedFigureBrackets = 0;
        for (Paragraph paragraph : text.getParagraphs()) {
            if (paragraph.getText().contains("{")) {
                openedFigureBrackets++;
            }
            if (openedFigureBrackets > 0 && !codeStarted
                    && paragraph.getText().toLowerCase().contains("class")) {
                codeStarted = true;
            }
            if (codeStarted) {
                paragraph.setCode(true);
            }
            if (paragraph.getText().contains("}")) {
                openedFigureBrackets--;
            }
            if (openedFigureBrackets == 0) {
                codeStarted = false;
            }
            if (!paragraph.isCode()) {
                for (String definedWord : JAVA_WORDS_ON_START) {
                    if (paragraph.getText().toLowerCase().trim().startsWith(definedWord)) {
                        paragraph.setCode(true);
                        break;
                    }
                }
            }
        }
    }
    /**
     * The list of defined Java words which can be on the start of the code
     * block.
     */
    private static final List<String> JAVA_WORDS_ON_START = Arrays.asList(
            "/*", "/**", "//", "}", "*", "import", "package", "class", "return",
            "public", "private", "protected", "if", "return", "else",
            "abstract", "default", "do", "implements", "boolean", "double",
            "try", "break", "short", "void", "byte", "enum", "int", "static",
            "volatile", "case", "extends", "interface", "strictfp", "while",
            "catch", "final", "long", "super", "system");
}