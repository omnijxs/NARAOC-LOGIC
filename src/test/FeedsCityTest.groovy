package test

import game.Game
import org.junit.Before
import org.junit.Test
import resources.city.City
import resources.common.GameMap
import resources.common.Tile
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit

/**
 * Created by Juri on 2.11.2015.
 */
class FeedsCityTest {

    // TODO Do not City class but a generic mocker class!!!

    protected Game game
    protected GameMap gameMap
    protected City city
    protected Tile nonCityTile
    protected Tile cityTile
    protected PopUnit armyInsideCity
    protected PopUnit nonArmyInsideCity
    protected PopUnit nonArmyoutsideCity

    @Before
    void setUp(){
        game = new Game()                       // TODO RETHINK THIS SHIT! USE BASE CLASS!!!
        gameMap = new GameMap()
        game.map = gameMap
        gameMap.game = game

        city = new City()
        game.map.cities = [city]

        nonCityTile = new Tile(map: gameMap)
        cityTile = new Tile(map: gameMap)
        city.tile = cityTile

        armyInsideCity = new ArmyUnit()
        nonArmyInsideCity = new PopUnit(priority: 2)    // TODO A fancier way of doing te priority sort
        nonArmyoutsideCity = new PopUnit(priority: 2)    // TODO A fancier way of doing te priority sort

        game.map.tiles = [cityTile, nonCityTile]

        game.popUnits = [armyInsideCity, nonArmyInsideCity]


    }

    @Test
    void testFeedCityTile() {

        armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile

        assert city.feedCity(2) == 0
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving
    }

    @Test
    void testFeedCityTileAndPreferred() {

        armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile
        nonArmyoutsideCity.tile = cityTile

        // assert city.feedCity(3) == 0
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving
        assert !nonArmyoutsideCity.starving
    }
}
