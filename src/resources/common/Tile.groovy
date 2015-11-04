package resources.common

import resources.popUnit.PopUnit

/**
 * Created by Juri on 21.10.2015.
 */
class Tile {

    GameMap gameMap
    Integer x
    Integer y
    
    List<PopUnit> popUnitsOnTile(){
        return gameMap.popUnits.findAll { it.tile == this }
    }

    // TODO TESTS
    def hasCity(){
        return gameMap.cities.find { it.tile == this }
    }
        
}
