package depot;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class WordsDepot
{
    abstract public void addWord(String word);
    abstract public void removeWord(String word);
    abstract public int wordOccurenceCount(String word);
    abstract public int patternOccurenceCount(String pattern);
    abstract public void serializeMe(String fileName) throws IOException;
    abstract public void deserializeMe(String file) throws IOException, ClassNotFoundException;
    void addFile(String fileName) throws IOException
    {
        Scanner scanner = new Scanner(new File(fileName));
        scanner.useDelimiter("[^a-zA-Z]+");
        while (scanner.hasNextLine())
        {
            try {
                String word = scanner.next();
                addWord(word);
            }catch (Exception e)
            {
            }
        }
        scanner.close();
    }
}
