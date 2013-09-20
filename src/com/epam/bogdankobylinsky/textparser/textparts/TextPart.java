package com.epam.bogdankobylinsky.textparser.textparts;

import java.util.Objects;

/**
 * Defines the part of text. Contains the String of part and the method for
 * getting it. Also overrided method for calculating the hashCode of TextPart
 * object.
 *
 * @author Bogdan Kobylinsky.
 */
public class TextPart {

    /**
     * The String of part of text.
     */
    private String text;

    /**
     * Creating the TextPart object with initial String.
     *
     * @param text is the String of text.
     */
    public TextPart(String text) {
        this.text = text;
    }

    /**
     * Getting the text.
     *
     * @return the String of text.
     */
    public String getText() {
        return text;
    }

    /**
     * Getting the hashCode of TextPart object.
     *
     * @return the hashCode of TextPart object.
     */
    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TextPart other = (TextPart) obj;
        return Objects.equals(this.text, other.text);
    }
}