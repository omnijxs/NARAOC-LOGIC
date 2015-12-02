package traits

import game.GameData

/**
 * Created by jxs on 2.12.2015.
 */
trait Multiplies implements Probability {

    def multiply(){
        if(resolveMultiply()){
            if(getProbability(multiplicationRate)){
                return breed()
            }
        }
    }

    def breed(){
        return this.class.newInstance()
    }

}