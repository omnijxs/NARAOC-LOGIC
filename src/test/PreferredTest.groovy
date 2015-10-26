package test

import org.junit.Test
import resources.common.Product
import resources.popUnit.ArmyUnit
import resources.city.City
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import resources.common.Tile

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

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 1)

        p.resolvePreferredCity([c])

        assert p.preferredCity == null
    }

    /** Distance equals demand = Pop unit has no preferred city */
    @Test
    void testDemandEqualsDistance() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 2)

        p.resolvePreferredCity([c])

        assert p.preferredCity == null
    }

    /** Demand is higher than distance = Pop unit has a preferred city */
    @Test
    void testEnoughDemand() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 3)
        p.resolvePreferredCity([c])

        assert p.preferredCity != null
        assert p.preferredCity == c
    }

    /** Army units do not have a preferred city */
    @Test
    void testArmyUnitHasNoPreferredCity() {

        PopUnit p = new ArmyUnit(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 2)

        p.resolvePreferredCity([c])

        assert p.preferredCity == null
    }


    /**
     *  X
     * Y#####
     *  #CP##
     *  #####
     *  ##C##
     *  #####
     *
     */
    /** Two cities with equal demand, city b is more distant */
    @Test
    void testCaseEqualDemandDistanceDecides() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City a = new City(tile: new Tile(x: 2, y: 2))    /** Distance 1 */
        City b = new City(tile: new Tile(x: 3, y: 4))    /** Distance 2 */

        a.demand.put(Product.FOOD, 2)
        b.demand.put(Product.FOOD, 2)

        p.resolvePreferredCity([a, b])

        assert p.preferredCity == a
    }

    /** Two cities with different demand, city b has more demand */
    @Test
    void testCaseHigherDemandDecides() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City a = new City(tile: new Tile(x: 2, y: 2))    /** Distance 1 */
        City b = new City(tile: new Tile(x: 3, y: 4))    /** Distance 2 */

        a.demand.put(Product.FOOD, 2)
        b.demand.put(Product.FOOD, 4)

        p.resolvePreferredCity([a, b])

        assert p.preferredCity == b
    }
}
