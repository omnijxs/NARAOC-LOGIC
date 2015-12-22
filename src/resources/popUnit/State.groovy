package resources.popUnit

import game.GameData
import resources.gameActor.GameActor
import resources.popUnit.obedience.Obedience
import resources.common.Race
import resources.common.Tile

/**
 * Created by Juri on 19.11.2015.
 */
class State {

    @Delegate Obedience obedience
    @Delegate Race race
    @Delegate Tile tile

    Integer age = 0

    Boolean isObedient(GameData gd, GameActor ga){
        return obedience.resolveObedience(gd, ga) > 0
    }
}
