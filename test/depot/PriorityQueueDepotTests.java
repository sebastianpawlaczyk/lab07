package depot;

import org.junit.jupiter.api.BeforeEach;

class PriorityQueueDepotTests extends DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new PriorityQueueDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}