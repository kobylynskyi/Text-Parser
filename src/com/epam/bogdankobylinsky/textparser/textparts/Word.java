package com.epam.bogdankobylinsky.textparser.textparts;

import java.util.HashMap;

/**
 * Defines one of the smallest part of text - the word. Contains the text of
 * word and method for adding new Word in the defined Map.
 *
 * @author Bogdan Kobylinsky.
 */
public class Word extends TextPart {

    /**
     * Creating new Word object.
     *
     * @param text is the text of word.
     */
    public Word(String text) {
        super(text);
    }

    /**
     * Getting the Word object. Creating new if it is not exists in the defined
     * set.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     * @param wordText is the String of sign.
     * @return the Sign object.
     */
    public static Word newWord(Text text, String wordText) {
        HashMap<Word, Integer> existingWords = text.getAllWords();
        // If the word already exists in the Map than incrementing its count in the Map.
        for (Word word : existingWords.keySet()) {
            if (word.getText().equals(wordText)) {
                Integer count = existingWords.get(word);
                existingWords.put(word, count + 1);
                return word;
            }
        }
        Word newWord = new Word(wordText);
        existingWords.put(newWord, 1);
        return newWord;
    }
}
