package traits

import resources.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsTile {

    /** COULD BE A TRAIT */
    Integer feedTile(Integer value){

        /** Feed all armies on the tile */
        def feedableUnitsOnTile = tile.popUnitsOnTile().findAll{ it.class == ArmyUnit }

        /** And yourself */
        feedableUnitsOnTile.add(this)

        // TODO make the common sort method where the priority of PopUnits is dealt with

        feedableUnitsOnTile.each {
            if(value)
                value = it.consume(value)
        }

        value

    }

}