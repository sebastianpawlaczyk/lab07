package depot;

import java.io.*;
import java.util.*;

public class ArrayListDepot extends WordsDepot implements java.io.Serializable
{
    private List<WordCounter> wordsArrayList_ = new ArrayList<>();

    @Override
    public void addWord(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsArrayList_.indexOf(w);
        if (idx < 0)
        {
            wordsArrayList_.add(w);
        }
        else
        {
            w = wordsArrayList_.get(idx);
            w.increment();
        }
    }

    @Override
    public void removeWord(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsArrayList_.indexOf(w);
        if (!(idx < 0))
        {
            wordsArrayList_.remove(idx);
        }
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        WordCounter w = new WordCounter(word);
        int idx = wordsArrayList_.indexOf(w);
        if (idx < 0)
        {
            return 0;
        }
        return wordsArrayList_.get(idx).getCounter();
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        for (WordCounter it : wordsArrayList_)
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
        depot = (ArrayListDepot) in.readObject();
        in.close();
        fileIn.close();
    }
}
