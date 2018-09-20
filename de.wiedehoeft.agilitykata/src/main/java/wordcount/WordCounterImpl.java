package wordcount;

public class WordCounterImpl implements WordCounter {
    @Override
    public int countFrom(String sentence) {
        return sentence.split(" ").length;
    }
}
