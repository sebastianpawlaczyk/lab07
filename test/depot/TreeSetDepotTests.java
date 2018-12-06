package depot;

import org.junit.jupiter.api.BeforeEach;

class TreeSetDepotTests extends  DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new TreeSetDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}