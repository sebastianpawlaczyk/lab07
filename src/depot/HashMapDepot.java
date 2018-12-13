package depot;

import java.io.*;
import java.util.*;

public class HashMapDepot extends WordsDepot implements java.io.Serializable
{
    private Map<String, Integer> wordsHashMap_ = new HashMap<>();

    @Override
    public void addWord(String word)
    {
        word = word.toLowerCase();
        if (wordsHashMap_.containsKey(word))
        {
            int counter = wordsHashMap_.get(word);
            counter++;
            wordsHashMap_.put(word, counter);
        }

        else
        {
            wordsHashMap_.put(word, 1);
        }
    }

    @Override
    public void removeWord(String word)
    {
        word = word.toLowerCase();
        wordsHashMap_.remove(word);
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        if (!wordsHashMap_.containsKey(word))
        {
            return 0;
        }

        return wordsHashMap_.get(word);
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        pattern = pattern.toLowerCase();

        for (Map.Entry<String, Integer> it : wordsHashMap_.entrySet())
        {
            if (it.getKey().contains(pattern))
            {
                counter = counter + it.getValue();
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
        depot = (HashMapDepot) in.readObject();
        in.close();
        fileIn.close();
    }
}
