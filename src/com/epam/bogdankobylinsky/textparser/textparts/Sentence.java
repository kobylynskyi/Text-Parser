package com.epam.bogdankobylinsky.textparser.textparts;

import java.util.ArrayList;

/**
 * Defines the Sentence as the part of Paragraph. Contains the list of words and
 * punctuation and special signs.
 *
 * @author Bogdan
 */
public class Sentence extends TextPart {

    /**
     * The list of Word and Sign objects.
     */
    private ArrayList<TextPart> wordsAndPunctuations;

    /**
     * Constructor for creating the Sentence object which contains the defined
     * string.
     *
     * @param unit is the sentence text.
     */
    public Sentence(String unit) {
        super(unit);
    }

    /**
     * Initializing new list object defined size.
     *
     * @param size is the size of list.
     */
    public void createNewWordsAndPunctuations(int size) {
        wordsAndPunctuations = new ArrayList<>(size);
    }

    /**
     * Getting the list of words and signs.
     *
     * @return the list of Word and Sign objects.
     */
    public ArrayList<TextPart> getWordsAndPunctuations() {
        return wordsAndPunctuations;
    }

    /**
     * Adding new Word or Sign to the list.
     *
     * @param newPart is the new Word or Sign.
     */
    public void addPart(TextPart newPart) {
        wordsAndPunctuations.add(newPart);
    }
}