package resources.popUnit

import resources.common.Tile

/**
 * Created by Juri on 19.11.2015.
 */
class State {

    @Delegate Tile tile
    // @Delegate Race race
    // @Delegate Obedience obedience

    Integer age = 0
    Integer multiplicationRate = 0  // TODO comes from race

    Boolean resolveMultiply(){
        true
    }
}
