package resources.popUnit

import resources.popUnit.obedience.Obedience
import resources.common.Race
import resources.common.Tile

/**
 * Created by Juri on 19.11.2015.
 */
class State {

    @Delegate Tile tile
    @Delegate Race race
    @Delegate Obedience obedience

    Integer age = 0

    Boolean resolveMultiply(){
        true
    }
}
