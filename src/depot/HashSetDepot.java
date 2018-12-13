package depot;

import java.io.*;
import java.util.*;

public class HashSetDepot extends WordsDepot implements java.io.Serializable
{
    private Set<WordCounter> wordsHasSet_ = new HashSet<>();

    @Override
    public void addWord(String word)
    {
        WordCounter w = new WordCounter(word);
        for (WordCounter it : wordsHasSet_)
        {
            if (it.getWord().equals(word))
            {
                it.increment();
                return;
            }
        }

        wordsHasSet_.add(w);
    }

    @Override
    public void removeWord(String word)
    {
        WordCounter w = new WordCounter(word);
        for (WordCounter it : wordsHasSet_) {
            if (it.getWord().equals(word))
            {
                wordsHasSet_.remove(w);
                return;
            }
        }
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        for (WordCounter it : wordsHasSet_)
        {
            if (it.getWord().equals(word))
            {
                return it.getCounter();
            }
        }
        return 0;
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        for (WordCounter it : wordsHasSet_)
        {
            if (it.getWord().contains(pattern))
            {
                counter = counter + it.getCounter();
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
}
