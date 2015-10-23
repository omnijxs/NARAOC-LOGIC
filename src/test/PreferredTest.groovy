package test

import org.junit.Test
import resources.City
import resources.PopUnit
import resources.Tile

/**
 * Created by Juri on 23.10.2015.
 */
class PreferredTest {

    /**
     *  X
     * Y#####
     *  ##P##
     *  #####
     *  ##C##
     *  #####
     *
     */
    /** Distance is less than demand = Pop unit has no preferred city */
    @Test
    void testNotEnoughDemand() {

        PopUnit p = new PopUnit(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2), demandForFood: 1)    /** Distance 2 */

        p.resolvePreferredCity([c])

        assert p.preferredCity == null
    }

    /** Distance equals demand = Pop unit has no preferred city */
    @Test
    void testDemandEqualsDistance() {

        PopUnit p = new PopUnit(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2), demandForFood: 2)    /** Distance 2 */

        p.resolvePreferredCity([c])

        assert p.preferredCity == null
    }

    /** Demand is higher than distance = Pop unit has a preferred city */
    @Test
    void testEnoughDemand() {

        PopUnit p = new PopUnit(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2), demandForFood: 3)    /** Distance 2 */

        p.resolvePreferredCity([c])

        assert p.preferredCity != null
        assert p.preferredCity == c
    }
}
