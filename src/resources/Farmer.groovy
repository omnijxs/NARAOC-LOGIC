package resources

/**
 * Created by Juri on 21.10.2015.
 */

class Farmer extends PopUnit {
    
    @Overwrite
    def produce(){
    
    }
    
    private Integer feedTile(){      
    
        def production 
        
        // TODO null check. MAKE TEST!
        def armyUnitsOnTile = tile.popUnitsOnTile().findAll{ it != this && it.class == ArmyUnit }
        
        armyUnitsOnTile.each { production = it.consume(production) }
        
        production
        
    }
}