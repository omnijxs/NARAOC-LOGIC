package resources.popUnit

import game.GameData
import resources.common.Priority
import resources.common.Race
import resources.common.Tile
import resources.gameActor.GameActor
import resources.popUnit.obedience.Obedience

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    @Delegate Obedience obedience
    @Delegate Race race
    @Delegate Tile tile

    Integer age = 0
    Priority priority

    public Boolean canMultiply(){
        return starving
    }
    
    public Boolean canReallocate(GameData gd, GameActor ga){
        return isObedient(gd, ga)
    }
    
    public Boolean canBeTaxed(GameData gd, GameActor ga){
        return isObedient(gd, ga)
    }
    
    private Boolean isObedient(GameData gd, GameActor ga){
        return resolveObedience(gd, ga) > 0
    }

    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

}
