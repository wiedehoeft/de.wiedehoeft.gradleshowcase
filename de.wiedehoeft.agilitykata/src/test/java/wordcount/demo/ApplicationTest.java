package wordcount.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wordcount.Application;
import wordcount.IOOperations;
import wordcount.WordCounter;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationTest {

    @Mock
    private IOOperations ioOperations;

    @Mock
    private WordCounter wordCounter;

    private Application application;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        application = new Application(ioOperations, wordCounter);
    }

    @Test
    void getUserInput() throws IOException {

        // Arrange
        when(ioOperations.getInput()).thenReturn("Mary had a little lamb");

        // Act
        application.getSentence();

        // Assert
        assertThat(application.getModel().getSentence()).isEqualTo("Mary had a little lamb");
    }

    @Test
    void printResult() throws IOException {
        String sentence = "Mary had a little lamb";
        application.getModel().setSentence(sentence);
        when(wordCounter.countFrom(sentence)).thenReturn(5);

        // Act
        application.printResult();

        // Assert
        verify(ioOperations).printOutput("Number of words: 5");
    }
}
