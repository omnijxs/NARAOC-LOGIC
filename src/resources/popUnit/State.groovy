package resources.popUnit

import resources.common.Tile

/**
 * Created by Juri on 19.11.2015.
 */
class State {

    @Delegate Tile tile

    Integer age = 0
    Integer multiplicationRate = 0  // TODO comes from race

    // @Delegate Race race
    // @Delegate Obedience obedience

    Boolean resolveMultiply(){
        true
    }
}
