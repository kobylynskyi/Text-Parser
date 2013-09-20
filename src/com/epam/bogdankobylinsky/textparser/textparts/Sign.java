package com.epam.bogdankobylinsky.textparser.textparts;

/**
 * Defines one of the smallest part of text - the sign. Contains the text of
 * sign and method for adding new Sign in the defined Set.
 *
 * @author Bogdan Kobylinsky.
 */
public class Sign extends TextPart {

    /**
     * Creating new Sign object.
     *
     * @param text is the text of sign.
     */
    public Sign(String text) {
        super(text);
    }

    /**
     * Getting the Sign object. Creating new if it is not exists in the defined
     * set.
     *
     * @param text is the link on the
     * {@link com.epam.bogdankobylinsky.textparser.textparts.Text} object
     * @param signText is the String of sign.
     * @return the Sign object.
     */
    public static Sign newSign(Text text, String signText) {
        for (Sign sign : text.getAllSigns()) {
            if (sign.getText().equals(signText)) {
                return sign;
            }
        }
        Sign newSign = new Sign(signText);
        text.getAllSigns().add(newSign);
        return newSign;
    }
}
