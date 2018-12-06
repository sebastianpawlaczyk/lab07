package depot;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class HashSetDepot extends WordsDepot implements java.io.Serializable
{
    private class WordAndCounter
    {
        public WordAndCounter(String word)
        {
            word_ = word;
            counter_ = 1;
        }
        public String word_;
        int counter_;
    }

    private Set<WordAndCounter> wordsHasSet_ = new HashSet<>();

    @Override
    public void addWord(String word)
    {
        word = word.toLowerCase();

        for (WordAndCounter it : wordsHasSet_)
        {
            if (it.word_.equals(word))
            {
                it.counter_++;
                return;
            }
        }
        wordsHasSet_.add(new WordAndCounter(word));
    }

    @Override
    public void removeWord(String word)
    {
        for (WordAndCounter it : wordsHasSet_)
        {
            if (it.word_.equals(word))
            {
                it.counter_ = 0;
            }
        }
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        word  = word.toLowerCase();

        for (WordAndCounter it : wordsHasSet_)
        {
            if (it.word_.equals(word))
            {
                return it.counter_;
            }
        }

        return 0;
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        pattern = pattern.toLowerCase();

        for (WordAndCounter it : wordsHasSet_)
        {
            if (it.word_.contains(pattern))
            {
                counter = counter + it.counter_;
            }
        }

        return counter;
    }

    @Override
    public void serializeMe(String fileName) throws IOException
    {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    @Override
    public void deserializeMe(String file) throws IOException, ClassNotFoundException
    {
        WordsDepot depot = null;
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        depot = (HashSetDepot) in.readObject();
        in.close();
        fileIn.close();
    }

    public static void main(String args[]) throws Exception
    {
        WordsDepot depot = new HashSetDepot();

        depot.addFile("RomanEmpire1.txt");
        depot.addFile("RomanEmpire2.txt");
        depot.addFile("RomanEmpire3.txt");
        depot.addFile("RomanEmpire4.txt");
        depot.addFile("RomanEmpire5.txt");
        depot.addFile("RomanEmpire6.txt");

        //Measure time
        //1
        long startTime = System.nanoTime();
        System.out.println(depot.patternOccurenceCount("no"));
        long endTime = System.nanoTime();
        long resultInMilliSeconds = (endTime - startTime) / 1000000;
        System.out.println(resultInMilliSeconds);
    }
}
