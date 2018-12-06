package depot;

import org.junit.jupiter.api.BeforeEach;

class VectorDepotTests extends DepotBaseTests
{
    @Override
    @BeforeEach
    public void createDepotClass()
    {
        depot = new VectorDepot();
    }

    @Override
    @BeforeEach
    public void addSomeWords()
    {
        super.addSomeWords();
    }
}