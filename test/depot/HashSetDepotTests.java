package depot;

import org.junit.jupiter.api.BeforeEach;

class HashSetDepotTests extends DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new HashSetDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}