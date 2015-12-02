package traits

import game.GameData
import resources.popUnit.PopUnit

/**
 * Created by jxs on 2.12.2015.
 */
trait Multiplies implements Probability {

    def resolveMultiply(GameData gd){
        if(state.resolveMultiply()){
            if(getProbability(state.multiplicationRate)){
                return multiply()
            }
        }
    }

    def multiply(){
        // TODO resolve correct PopUnitClass, set variables and change Tile.
    }

}