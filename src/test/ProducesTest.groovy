package test

import org.junit.Test
import traits.Produces

/**
 * Created by Juri on 17.11.2015.
 */
class ProducesTest implements Produces {

    // TODO ADD GameData 
    
    @Test
    void testProduce() {

        productAmount = 2

        assert produce() == 2
        assert harvestAmount == 2
    }

    @Test
    void testHarvest() {

        harvestAmount = 2

        assert harvest() == 2
        assert harvestAmount == 0
    }

    @Test
    void testProduceHarvestCycle() {

        productAmount = 2

        assert produce() == 2
        assert harvest() == 2

        assert harvestAmount == 0
        assert productAmount == 2
    }
}
