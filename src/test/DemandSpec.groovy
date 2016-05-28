import game.GameData
import org.junit.Test
import resources.common.Product
import resources.common.Tile
import resources.popHub.City
import resources.popUnit.PopUnit
import spock.lang.IgnoreRest
import spock.lang.Shared
import spock.lang.Specification
import traits.Preferres


/**
 * Created by jxs on 26.5.2016.
 */
class DemandSpec extends Specification {

    @Shared
    protected City city

    @Shared
    protected Tile cityTile

    private class MockUnit extends PopUnit implements Preferres {
        Tile tile
    }

    void setup(){
        cityTile = new Tile()
        city = new City(tile: cityTile)
    }

    def "No popUnit. No demand."(){

        when:
        city.setDemands(null, city)

        then:
        city.foodDemand() == 0
        city.workDemand() == 0
        city.tradeDemand() == 0
    }

    def "PopUnits in city proper."(){
        setup:
        PopUnit a = new MockUnit(tile: cityTile, preferredHub: null)

        when:
        city.setDemands([a], city)

        then:
        city.foodDemand() == 1
        city.workDemand() == 1
        city.tradeDemand() == 1
    }

    def "PopUnits outside city proper but prefers."() {
        setup:
        PopUnit a = new MockUnit(tile: null, preferredHub: city)

        when:
        city.setDemands([a], city)

        then:
        city.foodDemand() == 1
        city.workDemand() == 1
        city.tradeDemand() == 1
    }

    def "PopUnits in and outside city proper."() {
        setup:
        PopUnit a = new MockUnit(tile: null, preferredHub: city)
        PopUnit b = new MockUnit(tile: cityTile, preferredHub: null)

        when:
        city.setDemands([a, b], city)

        then:
        city.foodDemand() == 2
        city.workDemand() == 2
        city.tradeDemand() == 2
    }

    def "PopUnits outside city proper. No demand."() {
        setup:
        PopUnit a = new MockUnit(tile: new Tile(), preferredHub: new City())

        when:
        city.setDemands([a], city)

        then:
        city.foodDemand() == 0
        city.workDemand() == 0
        city.tradeDemand() == 0
    }
}