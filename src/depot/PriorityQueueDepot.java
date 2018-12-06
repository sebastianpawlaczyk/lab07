package depot;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PriorityQueueDepot extends WordsDepot implements java.io.Serializable
{
    private Queue<String> wordsPriorityQueue_ = new PriorityQueue<>();

    @Override
    public void addWord(String word)
    {
        word = word.toLowerCase();
        wordsPriorityQueue_.add(word);
    }

    @Override
    public void removeWord(String word)
    {
        word = word.toLowerCase();
        wordsPriorityQueue_.removeIf(word::equals);
        //wordsPriorityQueue_.removeAll(Collections.singleton(word));
    }

    @Override
    public int wordOccurenceCount(String word)
    {
        int counter = 0;
        word  = word.toLowerCase();

        for (String it : wordsPriorityQueue_)
        {
            if (it.equals(word))
            {
                counter++;
            }
        }

        return counter;
    }

    @Override
    public int patternOccurenceCount(String pattern)
    {
        int counter = 0;
        pattern = pattern.toLowerCase();

        for (String it : wordsPriorityQueue_)
        {
            if (it.contains(pattern))
            {
                counter++;
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
        depot = (PriorityQueueDepot) in.readObject();
        in.close();
        fileIn.close();
    }

    public static void main(String args[]) throws Exception
    {
        WordsDepot depot = new PriorityQueueDepot();

        depot.addFile("RomanEmpire1.txt");
        depot.addFile("RomanEmpire2.txt");
        depot.addFile("RomanEmpire3.txt");
        depot.addFile("RomanEmpire4.txt");
        depot.addFile("RomanEmpire5.txt");
        depot.addFile("RomanEmpire6.txt");

        //Measure time
        //1
        long startTime = System.nanoTime();
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
        long endTime = System.nanoTime();
        long resultInMilliSeconds = (endTime - startTime) / 1000000;
        System.out.println(resultInMilliSeconds);
    }
}
