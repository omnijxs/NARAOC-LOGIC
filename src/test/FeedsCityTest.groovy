package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popHub.City
import resources.common.GameMap
import resources.common.Tile
import resources.popUnit.ArmyUnit
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit

/**
 * Created by Juri on 2.11.2015.
 */
class FeedsCityTest {

    protected GameData gameData
    protected GameMap gameMap
    protected City city
    protected Tile nonCityTile
    protected Tile cityTile
    protected PopUnit armyInsideCity
    protected PopUnit nonArmyInsideCity
    protected PopUnit nonArmyOutsideCity

    @Before
    void setUp(){
        gameData = new GameData()                       // TODO RETHINK THIS SHIT! USE BASE CLASS!!!
        gameMap = new GameMap()
        gameData.gameMap = gameMap
        gameMap.gameData = gameData

        city = new City()
        gameData.gameMap.cities = [city]

        nonCityTile = new Tile()
        cityTile = new Tile()
        city.tile = cityTile

        armyInsideCity = new ArmyUnit()
        nonArmyInsideCity = new Merchant(priority: 2)    // TODO A fancier way of doing te priority sort
        nonArmyOutsideCity = new Farmer(priority: 2)    // TODO A fancier way of doing te priority sort

        gameData.gameMap.tiles = [cityTile, nonCityTile]

        gameData.popUnits = [armyInsideCity, nonArmyInsideCity]

    }

    @Test
    void testFeedCityTile() {

        armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile

        assert city.feedCity(gameData, 2) == 0
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving
    }

    @Test
    void testFeedCityTileAndPreferred() {

        armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile

        nonArmyOutsideCity.tile = nonCityTile
        nonArmyOutsideCity.preferredCity = city
        gameData.popUnits.add(nonArmyOutsideCity)

        assert city.feedCity(gameData, 3) == 0
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving
        assert !nonArmyOutsideCity.starving
    }

    @Test
    void testFeedCityTileAndDisregardNonPreferred() {

        armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile

        nonArmyOutsideCity.tile = nonCityTile
        nonArmyOutsideCity.preferredCity = null
        gameData.popUnits.add(nonArmyOutsideCity)

        assert city.feedCity(gameData, 3) == 1
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving
        assert nonArmyOutsideCity.starving
    }

}
