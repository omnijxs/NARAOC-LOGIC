package traits

import game.GameData
import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsTile implements PopUnitSorter, Feeds {

    /** Assumptions: I am implemented by an object which has the property Tile.
      * I need it to find the PopUnits to feed. */
    
    Integer feedTile(GameData gd, Integer foodAmount){

        /** Feed all armies on the tile and yourself */
        // TODO find all popUnits on tile which are not starving!!! 
        def popUnitsOnTile = gd.popUnits.findAll {(it.class == ArmyUnit || it == this) && it.tile == tile }
            
        /** Sort by default priority. 1) Pop Unit class 2) Pop Unit age*/
        def sortedPopUnits = defaultSort(popUnitsOnTile)
        
        return feed(sortedPopUnits, foodAmount)

    }

}