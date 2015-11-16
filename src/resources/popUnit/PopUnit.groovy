package resources.popUnit

import game.Player
import resources.common.Product
import resources.common.Tile
import traits.Preferred

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit implements Preferred {
    
    Integer priority
    Integer age = 0    
    Tile tile
    
    Product product                             // TODO REMOVE
    Integer productAmount = 0                   // TODO REMOVE
    Boolean starving = true                     // TODO REMOVE
    
    Player owner

     // TODO REMOVE
    Integer consume(def food){
        starving = false
        food - 1
    }
    
    /** Technically this is an abstract method */ 
    def produce(){}
    
    def reallocate(){}
    
    def multiply(){}
    
    
}
