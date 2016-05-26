import game.GameData
import org.junit.Test
import resources.common.Product
import resources.common.Tile
import resources.popHub.City
import resources.popUnit.PopUnit
import spock.lang.Shared
import spock.lang.Specification
import traits.Preferres


/**
 * Created by jxs on 26.5.2016.
 */
class DemandSpec extends Specification {

    @Shared
    GameData gameData

    @Shared
    protected City city

    @Shared
    protected Tile cityTile

    private class MockUnit extends PopUnit implements Preferres {
        Tile tile
    }

    void setup(){
        gameData = new GameData()

        cityTile = new Tile()

        city = new City(tile: cityTile)

        gameData.popHubs = [city]
    }

    def "No popUnit. No demand."(){

        when:
        city.setDemand(gameData)

        then:
        city.demand.get(Product.FOOD) == 0
        city.demand.get(Product.WORK) == 0
        city.demand.get(Product.TRADE) == 0
    }


    def "PopUnits in city proper."(){
        setup:
        PopUnit a = new MockUnit(tile: cityTile, preferredHub: null)

        when:
        gameData.popUnits = [a]
        city.setDemand(gameData)

        then:
        city.demand.get(Product.FOOD) == 1
        city.demand.get(Product.WORK) == 1
        city.demand.get(Product.TRADE) == 1
    }

    def "PopUnits outside city proper."() {
        setup:
        PopUnit a = new MockUnit(tile: null, preferredHub: city)

        when:
        gameData.popUnits = [a]
        city.setDemand(gameData)

        then:
        city.demand.get(Product.FOOD) == 1
        city.demand.get(Product.WORK) == 1
        city.demand.get(Product.TRADE) == 1
    }


    def "PopUnits in and outside city proper."() {
        setup:
        PopUnit a = new MockUnit(tile: null, preferredHub: city)
        PopUnit b = new MockUnit(tile: cityTile, preferredHub: null)

        when:
        gameData.popUnits = [a, b]
        city.setDemand(gameData)

        then:
        city.demand.get(Product.FOOD) == 2
        city.demand.get(Product.WORK) == 2
        city.demand.get(Product.TRADE) == 2
    }

    def "PopUnits outside city proper. No demand."() {
        setup:
        PopUnit a = new MockUnit(tile: new Tile(), preferredHub: new City())

        when:
        gameData.popUnits = [a]
        city.setDemand(gameData)

        then:
        city.demand.get(Product.FOOD) == 0
        city.demand.get(Product.WORK) == 0
        city.demand.get(Product.TRADE) == 0
    }
}