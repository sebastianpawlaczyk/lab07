package depot;

import java.io.*;
import java.util.*;

public class VectorDepot extends WordsDepot implements java.io.Serializable
{
    public Vector<WordCounter> wordsVector_ = new Vector<>();

    @Override
    public void addWord(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsVector_.indexOf(w);
        if (idx < 0)
        {
            wordsVector_.add(w);
        }
        else
        {
            w = wordsVector_.get(idx);
            w.increment();
        }
    }

    @Override
    public void removeWord(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsVector_.indexOf(w);
        if (!(idx < 0))
        {
            wordsVector_.remove(idx);
        }
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsVector_.indexOf(w);
        if (idx < 0)
        {
            return 0;
        }
        return wordsVector_.get(idx).getCounter();
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        for (WordCounter it : wordsVector_)
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
        depot = (VectorDepot) in.readObject();
        in.close();
        fileIn.close();
    }
}
