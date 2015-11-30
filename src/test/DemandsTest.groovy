package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popHub.City
import resources.common.Product
import resources.common.Tile
import resources.popUnit.PopUnit
import traits.Preferres

/**
 * Created by Juri on 9.11.2015.
 */
class DemandsTest {

    protected GameData gameData
    protected City city
    protected Tile cityTile

    private class MockUnit extends PopUnit implements Preferres {
        Tile tile
    }

    @Before
    void setUp(){
        gameData = new GameData()                      

        cityTile = new Tile()
        
        city = new City(tile: cityTile)
        
        gameData.hubs = [city]
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
 
        PopUnit a = new MockUnit(tile: cityTile, preferredHub: null)

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 1
        assert city.demand.get(Product.WORK) == 1
        assert city.demand.get(Product.TRADE) == 1
    }
    
    @Test
    void testSetDemandPopUnitsOutsideCityProper() {
 
        PopUnit a = new MockUnit(tile: null, preferredHub: city)

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 1
        assert city.demand.get(Product.WORK) == 1
        assert city.demand.get(Product.TRADE) == 1
    }
    
    @Test
    void testSetDemandPopUnitsInAndOutCityProper() {
 
        PopUnit a = new MockUnit(tile: null, preferredHub: city)
        PopUnit b = new MockUnit(tile: cityTile, preferredHub: null)

        gameData.popUnits = [a, b]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 2
        assert city.demand.get(Product.WORK) == 2
        assert city.demand.get(Product.TRADE) == 2
    }
    
    @Test
    void testNoDemandPopUnitsOutsideCityProper() {
 
        PopUnit a = new MockUnit(tile: new Tile(), preferredHub: new City())

        gameData.popUnits = [a]
        city.setDemand(gameData)
        
        assert city.demand.get(Product.FOOD) == 0
        assert city.demand.get(Product.WORK) == 0
        assert city.demand.get(Product.TRADE) == 0
    }
}
