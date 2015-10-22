package resources

/**
 * Created by Juri on 21.10.2015.
 */

class Farmer extends PopUnit {
    
    @Override
    def produce(){
        Integer value = 2
        return feedTile(value)
    }
    
    private Integer feedTile(Integer value){

        /** Feed yourself AND all armies on the tile */
        def armyUnitsOnTile = tile.popUnitsOnTile().findAll{ it == this || it.class == ArmyUnit }
        
        armyUnitsOnTile.each { value = it.consume(value) }

        value
        
    }
}