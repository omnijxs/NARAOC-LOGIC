package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.GameMap
import resources.common.Product
import resources.popUnit.ArmyUnit
import resources.popHub.City
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.common.Tile
import resources.popUnit.Worker

/**
 * Created by Juri on 23.10.2015.
 */
class PreferresTest {

    protected GameData gameData
    protected GameMap gameMap
    protected City city

    @Before
    void setUp() {
        gameData = new GameData()
        gameMap = new GameMap()
        gameData.gameMap = gameMap

        /* gameData.popUnits = []
        gameMap.hubs = [] */

    }
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

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.FOOD, 1)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub == null
    }

    /** Distance equals demand = Pop unit has no preferred city */
    @Test
    void testDemandEqualsDistance() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.FOOD, 2)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub == null
    }

    /** Demand is higher than distance = Pop unit has a preferred city */
    @Test
    void testEnoughDemand() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.FOOD, 3)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub != null
        assert p.preferredHub == c
    }

    /** Demand is higher than distance = Worker has a preferred city */
    @Test
    void testEnoughDemandForWork() {

        PopUnit p = new Worker(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.WORK, 3)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub != null
        assert p.preferredHub == c
    }

    /** Demand is higher than distance = Merchant has a preferred city */
    @Test
    void testEnoughDemandForTrade() {

        PopUnit p = new Merchant(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.TRADE, 3)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub != null
        assert p.preferredHub == c
    }

    /** Army units do not have a preferred city */
    @Test
    void testArmyUnitHasNopreferredHub() {

        PopUnit p = new ArmyUnit(tile: new Tile(x: 2, y: 3))
        City c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [c]

        c.demand.put(Product.FOOD, 2)
        c.demand.put(Product.WORK, 2)
        c.demand.put(Product.TRADE, 2)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub == null
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

        gameData.popUnits = [p]
        gameMap.hubs = [a, b]

        a.demand.put(Product.FOOD, 2)
        b.demand.put(Product.FOOD, 2)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub == a
    }

    /** Two cities with different demand, city b has more demand */
    @Test
    void testCaseHigherDemandDecides() {

        PopUnit p = new Farmer(tile: new Tile(x: 2, y: 3))
        City a = new City(tile: new Tile(x: 2, y: 2))    /** Distance 1 */
        City b = new City(tile: new Tile(x: 3, y: 4))    /** Distance 2 */

        gameData.popUnits = [p]
        gameMap.hubs = [a, b]

        a.demand.put(Product.FOOD, 2)
        b.demand.put(Product.FOOD, 4)

        p.resolvepreferredHub(gameData)

        assert p.preferredHub == b
    }
    
    // TODO how to handle the situation if multiple cities have the same preferred value? Currently solution is randomity.
}
