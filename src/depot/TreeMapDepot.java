package depot;

import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

    public static void main(String args[]) throws Exception
    {
        WordsDepot depot = new TreeMapDepot();

        depot.addFile("RomanEmpire1.txt");
        depot.addFile("RomanEmpire2.txt");
        depot.addFile("RomanEmpire3.txt");
        depot.addFile("RomanEmpire4.txt");
        depot.addFile("RomanEmpire5.txt");
        depot.addFile("RomanEmpire6.txt");

        Scanner scanner = new Scanner(new File("Nostromo.txt"));
        scanner.useDelimiter("[^a-zA-Z]+");
        while (scanner.hasNextLine())
        {
            try {
                String word = scanner.next();
                System.out.println(word);
                depot.removeWord(word);
            }catch (Exception e)
            {
            }
        }

        //Measure time
        //1
        long startTime = System.nanoTime();
        System.out.println();
        long endTime = System.nanoTime();
        long resultInMilliSeconds = (endTime - startTime) / 1000000;
        System.out.println(resultInMilliSeconds);
    }
}
