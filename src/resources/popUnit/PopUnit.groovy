package resources.popUnit

import resources.common.Priority

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    @Delegate State state
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
        return obedience.resolveObedience(gd, ga) > 0
    }

    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

}
