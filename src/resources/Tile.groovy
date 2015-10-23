package resources

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
        
        
}
