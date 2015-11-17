package resources.popUnit

import game.Player
import resources.common.Product
import resources.common.Tile

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    Integer priority
    Integer age = 0
    Tile tile

    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

    Product product                             // TODO REMOVE
    Integer productAmount = 0                   // TODO REMOVE
    Player owner                                // TODO REMOVE

    /** Technically this is an abstract method */ 
    def produce(){}
    
}
