package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.city.City
import resources.common.GameMap
import resources.common.Product
import resources.common.Tile
import resources.popUnit.PopUnit

/**
 * Created by Juri on 9.11.2015.
 */
class DemandTest {

    protected GameData gameData
    protected GameMap gameMap
    protected City city
    protected Tile cityTile

    @Before
    void setUp(){
        gameData = new GameData()                      
        gameMap = new GameMap()
        gameData.gameMap = gameMap
        gameMap.gameData = gameData

        cityTile = new Tile()
        
        city = new City(tile: cityTile)
        
        gameMap.cities = [city]

    }

    @Test
    void testNoPopUnitNoDemand() {
       
        city.setDemand(gameData)

        assert city.demand.get(Product.FOOD) == 0
        assert city.demand.get(Product.WORK) == 0
        assert city.demand.get(Product.TRADE) == 0
    }
    
    @Test
    void testSetDemandPopUnitsInCityProper() {
 
        PopUnit a = new PopUnit(tile: cityTile, preferredCity: null)

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 1
        assert city.demand.get(Product.WORK) == 1
        assert city.demand.get(Product.TRADE) == 1
    }
    
    @Test
    void testSetDemandPopUnitsOutsideCityProper() {
 
        PopUnit a = new PopUnit(tile: null, preferredCity: city)

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 1
        assert city.demand.get(Product.WORK) == 1
        assert city.demand.get(Product.TRADE) == 1
    }
    
    @Test
    void testSetDemandPopUnitsInAndOutCityProper() {
 
        PopUnit a = new PopUnit(tile: null, preferredCity: city)
        PopUnit b = new PopUnit(tile: cityTile, preferredCity: null)

        gameData.popUnits = [a, b]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 2
        assert city.demand.get(Product.WORK) == 2
        assert city.demand.get(Product.TRADE) == 2
    }
    
    @Test
    void testNoDemandPopUnitsOutsideCityProper() {
 
        PopUnit a = new PopUnit(tile: new Tile(), preferredCity: new City())

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 0
        assert city.demand.get(Product.WORK) == 0
        assert city.demand.get(Product.TRADE) == 0
    }
}
