package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsTile implements PopUnitSorter, Feeds {

    Integer feedTile(GameData gd, Integer foodAmount){

        /** Feed all armies on the tile and yourself */
        // def popUnitsOnTile = tile.popUnitsOnTile().findAll{ it.class == ArmyUnit || it == this}
        def popUnitsOnTile = gd.popUnits.findAll {(it.class == ArmyUnit || it == this) && it.tile == tile }
            
        /** Sort by default priority */
        def sortedPopUnits = defaultSort(popUnitsOnTile)
        
        return feed(sortedPopUnits, foodAmount)

    }

}