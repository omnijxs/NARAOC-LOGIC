package traits

/**
 * Created by Juri on 04.12.2015.
 */
trait Reallocates {    
  
    /** Is Reallocate a trait for hub or unit???!!! */
    def reallocate(GameData gd, UserInput input, PopUnit popUnit, PopHub popHub){
        
        popUnit
        
        input.popUnitClass
        input.popUnitType /** For armyUnit and magicUnit type identification */
        
        /** Deal with obedience */
        
        /** Create the new popUnit and remove this one from gameDate.popUnits */
        
        /** Deal with possible changes to tile */
        
        /** What will be the syntax to identify correct PopUnit-type? String? Enum? */
        
    }

}