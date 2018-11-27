import fit.ColumnFixture;
import wordcount.WordCounterImpl;

public class WordCount extends ColumnFixture {

    public String sentence;

    public int wordCount() {
        return new WordCounterImpl().countFrom(sentence);
    }

    public int ignoreStopwords() {
        return 0;
    }
}
