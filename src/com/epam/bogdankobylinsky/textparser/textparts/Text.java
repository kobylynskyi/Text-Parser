package com.epam.bogdankobylinsky.textparser.textparts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Defines the text. Contains the String of text, list of paragraphs, containers
 * for Word and Sign objects.
 *
 * @author Bogdan Kobylinsky.
 */
public class Text {

    /**
     * The String of text.
     */
    private String text;
    /**
     * The list of Paragraph objects.
     */
    private ArrayList<Paragraph> paragraphs;
    /**
     * The map of all words in the text.
     */
    private HashMap<Word, Integer> allWords;
    /**
     * The set of signs in the text.
     */
    private HashSet<Sign> allSigns;

    /**
     * Creating new Text object with initial String.
     *
     * @param text is the String of text.
     */
    public Text(String text) {
        this.text = text;
        allWords = new HashMap<>();
        allSigns = new HashSet<>();
    }

    /**
     * Getting the text.
     *
     * @return a String of text.
     */
    public String getText() {
        return text;
    }

    /**
     * Initializing new list of defined size.
     *
     * @param size is the size of list.
     */
    public void createNewParagraphsList(int size) {
        paragraphs = new ArrayList<>(size);
    }

    /**
     * Getting the list of Paragraph objects.
     *
     * @return
     */
    public ArrayList<Paragraph> getParagraphs() {
        return paragraphs;
    }

    /**
     * Adding the Paragraph to the list.
     *
     * @param newPart is the Paragraph object.
     */
    public void addPart(Paragraph newParagraph) {
        paragraphs.add(newParagraph);
    }

    /**
     * Getting the Map of words.
     *
     * @return the Map of all Word objects of the text.
     */
    public HashMap<Word, Integer> getAllWords() {
        return allWords;
    }

    /**
     * Getting the Set of signs.
     *
     * @return the Set of all Sign objects of the text.
     */
    public HashSet<Sign> getAllSigns() {
        return allSigns;
    }
}