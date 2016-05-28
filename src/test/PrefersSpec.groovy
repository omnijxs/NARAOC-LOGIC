import game.GameData
import resources.common.Product
import resources.common.Tile
import resources.popHub.City
import resources.popHub.PopHub
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by jxs on 27.5.2016.
 */

class PrefersSpec extends Specification {

    @Shared
    GameData gameData

    void setup() {
        gameData = new GameData()
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
    def "Distance is less than demand: Pop unit has no preferred city."(){
        setup:
        Tile tile = new Tile(x: 2, y: 3)
        PopUnit a = new Farmer(tile: tile)
        PopHub c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 1)

        when:
        a.preferres([c], tile, Product.FOOD)

        then:
        !a.preferredHub
    }

    def "Demand is higher than distance. Pop unit has a preferred city."(){
        setup:
        Tile tile = new Tile(x: 2, y: 3)
        PopUnit a = new Farmer(tile: tile)
        PopHub c = new City(tile: new Tile(x: 1, y: 2))    /** Distance 2 */

        c.demand.put(Product.FOOD, 3)

        when:
        a.preferres([c], tile, Product.FOOD)

        then:
        a.preferredHub == c
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
    def "Two cities with equal demand. Closer one wins."(){
        setup:
        Tile tile = new Tile(x: 2, y: 3)
        PopUnit a = new Farmer(tile: tile)
        PopHub b = new City(tile: new Tile(x: 2, y: 2))    /** Distance 1 */
        PopHub c = new City(tile: new Tile(x: 3, y: 4))    /** Distance 2 */

        b.demand.put(Product.FOOD, 2)
        c.demand.put(Product.FOOD, 2)

        when:
        a.preferres([b, c], tile, Product.FOOD)

        then:
        a.preferredHub == b
    }

    def "Two cities with different demand."(){
        setup:
        Tile tile = new Tile(x: 2, y: 3)
        PopUnit a = new Farmer(tile: tile)
        PopHub b = new City(tile: new Tile(x: 2, y: 2))    /** Distance 1 */
        PopHub c = new City(tile: new Tile(x: 3, y: 4))    /** Distance 2 */

        b.demand.put(Product.FOOD, 2)
        c.demand.put(Product.FOOD, 4)

        when:
        a.preferres([b, c], tile, Product.FOOD)

        then:
        a.preferredHub == c
    }

    // TODO how to handle the situation if multiple cities have the same preferred value? Currently random.
    // TODO take into account Product.WORK and Product.TRADE
}