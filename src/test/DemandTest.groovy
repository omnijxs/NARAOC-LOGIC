package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.city.City
import resources.common.GameMap
import resources.common.Product

/**
 * Created by Juri on 9.11.2015.
 */
class DemandTest {

    protected GameData gameData
    protected GameMap gameMap

    @Before
    void setUp(){
        gameData = new GameData()                       // TODO RETHINK THIS SHIT
        gameMap = new GameMap()
        gameData.gameMap = gameMap
        gameMap.gameData = gameData

    }

    @Test
    void testNoPopUnitNoDemand() {

        City a = new City()

        gameMap.cities = [a]

        a.setDemand(gameData)

        assert a.demand.get(Product.FOOD) == 0
        assert a.demand.get(Product.WORK) == 0
        assert a.demand.get(Product.TRADE) == 0
    }
}
