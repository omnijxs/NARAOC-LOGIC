package resources.popUnit.obedience

import game.GameData
import resources.gameActor.GameActor

/**
 * Created by jxs on 2.12.2015.
 */
class Obedience {

    Integer value
    List<ViolationRule> violations

    public Obedience(){
        value = 100 /** From config */
        violations = []
    }

    Integer resolveObedience(GameData gd, GameActor ga){
        Integer temp = 0

        violations.each { it ->
            temp += it.resolve(gd, ga)
        }

        return value - temp

    }

    def violate(ViolationRule violation){
        violations.push(violation)
    }

    /*
    def removeObsolete(){

    }
    */

}