package resources

/**
 * Created by Juri on 21.10.2015.
 */
class Tile {
    
    GameMap map
    
    public Tile(GameMap map){
        this.map = map 
    }
    
    List<PopUnit> popUnitsOnTile(){
        return map.game.popUnits.findAll { it.tile == this }        
    }
        
        
}
