package depot;

import java.io.*;
import java.util.*;

public class TreeMapDepot extends WordsDepot implements java.io.Serializable
{
    private Map<String, Integer> wordsTreeMap_ = new TreeMap<>();

    @Override
    public void addWord(String word)
    {

        word = word.toLowerCase();
        if (wordsTreeMap_.containsKey(word))
        {
            int counter = wordsTreeMap_.get(word);
            counter++;
            wordsTreeMap_.put(word, counter);
        }

        else
        {
            wordsTreeMap_.put(word, 1);
        }
    }

    @Override
    public void removeWord(String word)
    {
        word = word.toLowerCase();
        wordsTreeMap_.remove(word);
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        if (!wordsTreeMap_.containsKey(word))
        {
            return 0;
        }

        return wordsTreeMap_.get(word);
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        pattern = pattern.toLowerCase();

        for (Map.Entry<String, Integer> it : wordsTreeMap_.entrySet())
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
        depot = (TreeMapDepot) in.readObject();
        in.close();
        fileIn.close();
    }
}
