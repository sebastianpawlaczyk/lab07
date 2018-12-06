package depot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class DepotBaseTests
{
    protected WordsDepot depot;

    @BeforeEach
    public abstract void createDepotClass();

    @BeforeEach
    public void addSomeWords()
    {
        depot.addWord("alphabet");
        depot.addWord("alphabet");
        depot.addWord("alphabet");
        depot.addWord("alphabet");
        depot.addWord("alphanumeric");
        depot.addWord("alphanumeric");
        depot.addWord("alphanumeric");
    }

    @Test
    public void addWordToDepotAndReturnProperWordOccurence()
    {
        Assertions.assertEquals(4, depot.wordOccurenceCount("alphabet"));
        Assertions.assertEquals(3, depot.wordOccurenceCount("alphanumeric"));
        Assertions.assertEquals(0, depot.wordOccurenceCount("alpha"));
    }

    @Test
    public void addWordToDepotAndReturnProperPatternOccurence()
    {
        Assertions.assertEquals(7, depot.patternOccurenceCount("alpha"));
        Assertions.assertEquals(3, depot.patternOccurenceCount("alphan"));
        Assertions.assertEquals(4, depot.patternOccurenceCount("alphab"));
    }

    @Test
    public void removeWordFromDepotAndReturnProperResults()
    {
        depot.removeWord("alphabet");
        Assertions.assertEquals(0, depot.wordOccurenceCount("alphabet"));
        Assertions.assertEquals(3, depot.patternOccurenceCount("alpha"));
        Assertions.assertEquals(0, depot.patternOccurenceCount("alphab"));

        depot.removeWord("alphanumeric");
        Assertions.assertEquals(0, depot.wordOccurenceCount("alphanumeric"));
        Assertions.assertEquals(0, depot.patternOccurenceCount("a"));
    }
}
