package de.wiedehoeft.hangmankata;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HangmanTest {

    private Hangman hangman;

    @Before
    public void setUp() throws Exception {
        hangman = new Hangman("bottle");
    }

    @Test
    public void noRightChar() {
        // Given
        char guess = 'a';

        // When
        String result = hangman.guess(guess);

        // Then
        assertThat(result).isEqualTo("------");
    }

    @Test
    public void oneRightChar() {
        // Given
        char guess = 'l';

        // When
        String result = hangman.guess(guess);

        // Then
        assertThat(result).isEqualTo("----l-");
    }

    @Test
    public void oneRightCharExistingTwice() {
        // Given
        char guess = 't';

        // When
        String result = hangman.guess(guess);

        // Then
        assertThat(result).isEqualTo("--tt--");
    }

    @Test
    public void twoRightChar() {
        // Given
        char guess = 't';
        char secondGuess = 'o';

        // When
        hangman.guess(guess);
        String result = hangman.guess(secondGuess);

        // Then
        assertThat(result).isEqualTo("-ott--");
    }

    @Test
    public void wholeWord() {
        assertThat(hangman.guess('b')).isEqualTo("b-----");
        assertThat(hangman.guess('a')).isEqualTo("b-----");
        assertThat(hangman.guess('t')).isEqualTo("b-tt--");
        assertThat(hangman.guess('o')).isEqualTo("bott--");
        assertThat(hangman.guess('l')).isEqualTo("bottl-");
        assertThat(hangman.guess('i')).isEqualTo("bottl-");
        assertThat(hangman.guess('e')).isEqualTo("bottle");
    }
}
