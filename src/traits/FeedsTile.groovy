package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsTile implements PopUnitSorter, Feeds {

    Integer feedTile(Integer value){

        /** Feed all armies on the tile and yourself */
        def popUnitsOnTile = tile.popUnitsOnTile().findAll{ it.class == ArmyUnit || it == this}

        /** Sort by default priority */
        def sortedPopUnits = prioritySortPopUnits(popUnitsOnTile)
        
        return feed(sortedPopUnits, value)

    }

}