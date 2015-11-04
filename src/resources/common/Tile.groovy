package resources.common

import resources.city.City
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.popUnit.Worker

/**
 * Created by Juri on 21.10.2015.
 */
class Tile {

    GameMap map
    Integer x
    Integer y
    
    List<PopUnit> popUnitsOnTile(){
        return map.game.popUnits.findAll { it.tile == this }        
    }
    
    // TODO not the optimal place for this method. Only here now because of access to popUnits!
    List<PopUnit> feedablePopUnits(City city){
        return map.game.popUnits.findAll { it.preferredCity == city && (it.class == Worker || Merchant) && !it.tile.hasCity() }
    }
    
    // TODO TESTS
    def hasCity(){
        return map.cities.find { it.tile == this }
    }
        
}
