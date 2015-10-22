package resources

/**
 * Created by Juri on 21.10.2015.
 */
class Tile {
    
    List<PopUnit> popUnitsOnTile(){
     
        // TODO does not resolve how to access this list of PopUnits
        return popUnits.findAll { it.tile == this }
        
    }
        
        
}
