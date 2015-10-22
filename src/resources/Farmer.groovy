package resources

/**
 * Created by Juri on 21.10.2015.
 */

class Farmer extends PopUnit {
    
    @Override
    def produce(){
        return feedTile(2) >= 0 ? feedTile(2) : 0
    }

    /** COULD BE A TRAIT */
    private Integer feedTile(Integer value){

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