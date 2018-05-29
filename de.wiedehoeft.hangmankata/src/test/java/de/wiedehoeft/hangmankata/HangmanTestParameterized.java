package de.wiedehoeft.hangmankata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Showcase. Lacks the possibility to make multiple guesses and expectations. It should be possible with more complex array.
 */
@RunWith(Parameterized.class)
public class HangmanTestParameterized {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {'a', "------"},
                {'l', "----l-"},
                {'t', "--tt--"},
        });
    }

    private char guess;
    private String result;

    public HangmanTestParameterized(char guess, String result) {
        this.guess = guess;
        this.result = result;
    }

    @Test
    public void guessChar() {
        Hangman hangman = new Hangman("bottle");

        assertThat(hangman.guess(guess)).isEqualTo(result);
    }
}
