package depot;

import java.io.*;
import java.util.*;

public class HashTableDepot extends WordsDepot implements java.io.Serializable
{
    private Hashtable<String, Integer> wordsHashTable_ = new Hashtable<>();

    @Override
    public void addWord(String word)
    {
        word = word.toLowerCase();
        if (wordsHashTable_.containsKey(word))
        {
            int counter = wordsHashTable_.get(word);
            counter++;
            wordsHashTable_.put(word, counter);
        }

        else
        {
            wordsHashTable_.put(word, 1);
        }
    }

    @Override
    public void removeWord(String word)
    {
        word = word.toLowerCase();
        wordsHashTable_.remove(word);
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        if (!wordsHashTable_.containsKey(word))
        {
            return 0;
        }

        return wordsHashTable_.get(word);
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        pattern = pattern.toLowerCase();

        for (Map.Entry<String, Integer> it : wordsHashTable_.entrySet())
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
        depot = (HashTableDepot) in.readObject();
        in.close();
        fileIn.close();
    }
}
