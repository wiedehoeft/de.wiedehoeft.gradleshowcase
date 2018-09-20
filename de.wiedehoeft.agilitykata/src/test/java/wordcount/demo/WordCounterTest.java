package wordcount.demo;

import org.junit.jupiter.api.Test;
import wordcount.WordCounterImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class WordCounterTest {

    @Test
    void countWords() {
        String sentence = "Mary had a little lamb";

        int wordCount = new WordCounterImpl().countFrom(sentence);

        assertThat(wordCount).isEqualTo(5);
    }
}
