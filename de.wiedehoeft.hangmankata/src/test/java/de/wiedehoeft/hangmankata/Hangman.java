package de.wiedehoeft.hangmankata;

import java.util.ArrayList;
import java.util.List;

public class Hangman {
    private static final String ANY_CHAR_EXCEPT = "[^]";
    private static final String NON_GUESSED_CHAR_PLACEHOLDER = "-";

    private final String word;
    private final List<Character> guesses;
    private final StringBuilder regex;

    public Hangman(String word) {
        this.word = word;
        this.guesses = new ArrayList<>();
        this.regex = new StringBuilder();
        regex.append(ANY_CHAR_EXCEPT);
    }

    public String guess(char guess) {
        guesses.add(guess);

        guesses.forEach(current -> regex.insert(getIndexOfClosedSquareBracket(), current));

        return word.replaceAll(regex.toString(), NON_GUESSED_CHAR_PLACEHOLDER);
    }

    private int getIndexOfClosedSquareBracket() {
        return regex.lastIndexOf("]");
    }
}
