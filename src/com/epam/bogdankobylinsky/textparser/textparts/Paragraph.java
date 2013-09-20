package com.epam.bogdankobylinsky.textparser.textparts;

import java.util.ArrayList;

/**
 * Defines the separate part (paragraph) of the
 * {@link com.epam.bogdankobylinsky.textparser.textparts.Text}.
 *
 * @author Bogdan Kobylinsky.
 */
public class Paragraph extends TextPart {

    /**
     * Paragraph can contain plain text as well as the code.
     */
    private boolean code;
    /**
     * The list of sentences in paragraph.
     */
    private ArrayList<Sentence> sentences;

    /**
     * Constructor for creating the Paragraph object.
     *
     * @param unit
     */
    public Paragraph(String unit) {
        super(unit);
        sentences = new ArrayList<>();
    }

    /**
     * Getting the type of paragraph(code or plain text).
     *
     * @return {@code true} if the paragraph is the code block.
     */
    public boolean isCode() {
        return code;
    }

    /**
     * Setting the type of paragraph to <i>code</i>
     *
     * @param code {@code true} if the paragraph is the code block.
     */
    public void setCode(boolean code) {
        this.code = code;
    }

    /**
     * Getting the list of sentences.
     *
     * @return the list of sentences.
     */
    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

    /**
     * Adding new Sentence object to he list.
     *
     * @param newSentence
     */
    public void addSentence(Sentence newSentence) {
        sentences.add(newSentence);
    }
}