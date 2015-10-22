package traits

import resources.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsTile {

    Integer feedTile(Integer value){

        /** Feed all armies on the tile and yourself */
        def unsortedFeedableUnitsOnTile = tile.popUnitsOnTile().findAll{ it.class == ArmyUnit || it == this}

        /** Sort by default priority */
        def sortedFeedableUnitsOnTile = prioritySortPopUnits(unsortedFeedableUnitsOnTile)
        
        sortedFeedableUnitsOnTile.each {
            if(value)
                value = it.consume(value)
        }

        value

    }
    
    // TODO Turn into a trait or a utilityMethod
    private List<PopUnit> prioritySortPopUnits(List<PopUnit> popUnits){    
        return popUnits.sort { it.priority && !it.age }
        
    }

}