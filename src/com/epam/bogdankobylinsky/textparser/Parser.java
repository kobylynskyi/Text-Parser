package com.epam.bogdankobylinsky.textparser;

import com.epam.bogdankobylinsky.textparser.textparts.Paragraph;
import com.epam.bogdankobylinsky.textparser.textparts.Sentence;
import com.epam.bogdankobylinsky.textparser.textparts.Sign;
import com.epam.bogdankobylinsky.textparser.textparts.Text;
import com.epam.bogdankobylinsky.textparser.textparts.Word;

/**
 * Is designed for deep parsing of the text. Has methods for splitting the text
 * onto paragraphs, defining the paragraph if it is the code block, splitting
 * paragraphs onto sentences, sentences onto words and punctuation and special
 * signs.
 *
 * @author Bogdan Kobylinsky.
 */
public class Parser {

    /**
     * Parsing the text of
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object for
     * paragraphs, sentences, words and special signs.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     */
    public static void parseText(Text text) {
        Printer.print("\t[1/3]Splitting text onto paragraphs...");
        parseForParagraphs(text);
        Utils.scanParagraphsForCode(text);
        Printer.print("\t[2/3]Splitting paragraphs onto sentences...");
        parseForSentences(text);
        Printer.print("\t[3/3]Splitting sentences onto words and punctuations...");
        parseForWordsAndPunctuations(text);
    }

    /**
     * Parsing the text. Dividing it into the paragraphs and defining if it is a
     * code block.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     */
    private static void parseForParagraphs(Text text) {
        // Splitting the text into paragraphs 
        // using the line separator depending on the OS.
        String[] paragraphs = text.getText().split(System.getProperty("line.separator"));
        text.createNewParagraphsList(paragraphs.length);
        for (String paragraph : paragraphs) {
            if (paragraph.length() > 0) {
                text.addPart(new Paragraph(paragraph));
            }
        }
    }

    /**
     * Parsing the paragraphs of the text. Dividing it into the sentences.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     */
    private static void parseForSentences(Text text) {
        String paragraphText;
        int paragraphLength;
        int lastCut;
        for (Paragraph paragraph : text.getParagraphs()) {
            if (paragraph.isCode()) {
                continue;
            }
            paragraphText = paragraph.getText();
            paragraphLength = paragraphText.length();
            lastCut = 0;
            for (int i = 0; i < paragraphLength - 2; i++) {
                if (separateSentence(paragraphText, lastCut, i)) {
                    paragraph.addSentence(new Sentence(paragraphText.substring(lastCut, i + 1)));
                    lastCut = i + 2;
                }
            }
            paragraph.addSentence(new Sentence(paragraphText.substring(lastCut, paragraphLength)));
        }
    }

    /**
     * Method return <b>true</b> if occurs the sentence separator and there is
     * space after it and the next symbol is uppercase. Also skipping the title
     * numbers in the text.
     *
     * @param paragraphText is the text that is gonna be separated.
     * @param lastCut is the index of last cutted sentence
     * @param i is current position of index
     * @return <b>true</b> if occurs the sentence separator and there is space
     * after it and the next symbol is uppercase. Also skipping the title
     * numbers in the text. Otherwise - <b>false</b>.
     */
    private static boolean separateSentence(String paragraphText, int lastCut, int i) {
        return ".?!".indexOf(paragraphText.charAt(i)) >= 0
                && Character.isWhitespace(paragraphText.charAt(i + 1))
                && Character.isUpperCase(paragraphText.charAt(i + 2))
                && !paragraphText.substring(lastCut, i + 1).matches("^[﻿0-9. \t]+$")
                && i - lastCut > 2;
    }

    /**
     * Parsing the sentences of each paragraph of the text. Dividing them into
     * words and punctuation and special signs.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     */
    private static void parseForWordsAndPunctuations(Text text) {
        String[] sentenceParts;
        int lastCharIndex, firstCharIndex;
        for (Paragraph paragraph : text.getParagraphs()) {
            if (paragraph.isCode()) {
                continue;
            }
            for (Sentence sentence : paragraph.getSentences()) {
                sentenceParts = sentence.getText().split(" ");
                sentence.createNewWordsAndPunctuations(sentenceParts.length);
                for (String sentencePart : sentenceParts) {
                    if (sentencePart.length() > 0) {
                        // Adding the Sign and Word objects into the Sentence.
                        firstCharIndex = indexOfFirstLetter(sentencePart);
                        lastCharIndex = indexOfLastLetter(sentencePart);
                        for (int i = 0; i < firstCharIndex; i++) {
                            sentence.addPart(Sign.newSign(text, sentencePart.substring(i, i + 1)));
                        }
                        sentence.addPart(Word.newWord(text, sentencePart.substring(firstCharIndex, lastCharIndex + 1)));
                        for (int i = lastCharIndex + 1; i < sentencePart.length(); i++) {
                            sentence.addPart(Sign.newSign(text, sentencePart.substring(i, i + 1)));
                        }
                        sentence.addPart(Word.newWord(text, " "));
                    }
                }
            }
        }
    }

    /**
     * Finding the index of the first letter or digit which occurs in the
     * string.
     *
     * @param sentencePart is the string for scanning.
     * @return the index of the first letter or digit which occurs in the
     * string.
     */
    private static int indexOfFirstLetter(String sentencePart) {
        for (int i = 0; i < sentencePart.length(); i++) {
            if (Character.isLetterOrDigit(sentencePart.charAt(i))) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Finding the index of the last letter or digit which occurs in the string.
     *
     * @param sentencePart is the string for scanning.
     * @return the index of the last letter or digit which occurs in the string.
     */
    private static int indexOfLastLetter(String sentencePart) {
        for (int i = sentencePart.length() - 1; i >= 0; i--) {
            if (Character.isLetterOrDigit(sentencePart.charAt(i))) {
                return i;
            }
        }
        return sentencePart.length() - 1;
    }
}
