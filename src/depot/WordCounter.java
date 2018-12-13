package depot;

public class WordCounter implements Comparable<WordCounter>, java.io.Serializable
{

    protected int counter_;
    protected String word_;

    public WordCounter(String word) {
        word_ = word.toLowerCase();
        counter_ = 1;
    }

    public void increment() {
        counter_++;
    }

    public int getCounter()
    {
        return counter_;
    }

    public String getWord()
    {
        return word_;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WordCounter)) {
            return false;
        }
        String otherWord = ((WordCounter) obj).word_;
        return word_.equals(otherWord);
    }

    public int hashCode()
    {
        return word_.hashCode();
    }

    public String toString()
    {
        return ("["+word_+"\t"+counter_+"]");
    }

    @Override
    public int compareTo(WordCounter o) {
        return 0;
    }
}
