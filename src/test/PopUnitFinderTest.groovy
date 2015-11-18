package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.GameMap
import resources.common.Tile
import resources.popHub.City
import resources.popHub.PopHub
import resources.popUnit.PopUnit
import traits.PopUnitFinder
import traits.Preferres

/**
 * Created by Juri on 18.11.2015.
 */
class PopUnitFinderTest implements PopUnitFinder {

    protected GameData gameData
    protected GameMap gameMap
    protected PopHub city
    protected Tile cityTile

    private class MockUnit extends PopUnit implements Preferres {
        Tile tile
    }

    @Before
    void setUp(){
        gameData = new GameData()
        gameMap = new GameMap()
        gameData.gameMap = gameMap

        cityTile = new Tile()

        city = new City(tile: cityTile)

        gameMap.hubs = [city]

    }

    @Test
    void testpopHubPopulation() {

        PopUnit a = new MockUnit(tile: cityTile, preferredHub: city)
        PopUnit b = new MockUnit(tile: cityTile, preferredHub: new PopHub())
        PopUnit c = new MockUnit(tile: new Tile(), preferredHub: null)
        PopUnit d = new MockUnit(tile: new Tile(), preferredHub: new PopHub())

        gameData.popUnits = [a, b, c, d]

        /**
         * There is a conflict for popUnits that are in the city proper but which have a different preferredHub.
         * These are deal with differently depending on the fact are calculation production for the city or
         * feeding city pop Units. TODO later on split to two methods!!!
         */
        assert popHubPopulation(gameData, city) == [a, b]
    }
}
