package depot;

import org.junit.jupiter.api.BeforeEach;

class LinkedListDepotTests extends DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new LinkedListDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}