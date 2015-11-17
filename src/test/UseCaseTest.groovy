package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.city.City
import resources.common.GameMap
import resources.common.Product
import resources.common.Tile
import resources.popUnit.Farmer
import resources.popUnit.PopUnit

/**
 * Created by Juri on 16.11.2015.
 */
class UseCaseTest {

    protected GameData gameData
    protected GameMap gameMap
    protected City city
    protected Tile cityTile

    @Before
    void setUp() {
        gameData = new GameData()
        gameMap = new GameMap()
        gameData.gameMap = gameMap
        gameMap.gameData = gameData

        cityTile = new Tile(x: 1, y: 1)
        
        city = new City(tile: cityTile)

        gameMap.cities = [city]
        
        gameMap.popUnits = [new Farmer(tile: cityTile)]

    }

    @Test
    void testStub() {

        // TODO ADD DEMAND TO CITY
        gameMap.popUnits.each { p ->
            p.resolvePreferredCity([city])          // TODO should be gameData!!!
            
            /**TileFeeding popUnits feed their tiles and set the surplus as their this turns production.
             Also set the production “flags” up to their popUnits */
            p.produce()

            if(1 == 1){}
        }

       /*
       // Could be as threads!!!
       gameMap.each { c ->

           *//** Population of the city TODO make an own method *//*
            def popUnits = popUnits.findAll { it.preferredCity == c || it.tile == c.tile }

            Integer foodProd += popUnits.findAll { it.product == Produce.FOOD }.harvest()
            Integer workProd += popUnits.findAll { it.product == Produce.WORK }.harvest()
            Integer tradeProd += popUnits.findAll { it.product == Produce.TRADE }.harvest()

            Integer surplusFood = c.feedCity(popUnits, foodProd)

            *//** Calculate bonuses, deal with buildings etc. *//*
            CityProduction

                    cityProduction = c.produce(foodProd

                            , workProd, tradeProd, surplusFood)

            turnProduction.add(cityProduction)
*/
        }
}