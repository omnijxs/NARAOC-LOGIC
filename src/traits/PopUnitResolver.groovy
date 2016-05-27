package traits

import game.GameData
import resources.gameActor.GameActor

/**
 * Created by jxs on 27.5.2016.
 */
trait PopUnitResolver implements HasObedience {

    public Boolean canMultiply(Boolean starving){
        return !starving
    }

    public Boolean canReallocate(GameData gameData, GameActor gameActor){
        return isObedient(gameData, gameActor)
    }

    public Boolean canBeTaxed(GameData gameData, GameActor gameActor){
        return isObedient(gameData, gameActor)
    }

    private Boolean isObedient(GameData gameData, GameActor gameActor){
        return resolveObedience(gameData, gameActor) > 0
    }




}