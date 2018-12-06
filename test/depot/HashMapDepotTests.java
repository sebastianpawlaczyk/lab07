package depot;

import org.junit.jupiter.api.BeforeEach;


class HashMapDepotTests extends DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new HashMapDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}