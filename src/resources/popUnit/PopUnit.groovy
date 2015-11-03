package resources.popUnit

import resources.common.Product
import resources.common.Tile
import traits.Preferred

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit implements Preferred {
    
    Tile tile
    Product product
    Integer productAmount
    
    Boolean starving = true                 // TODO think thru the init process!!!
    Integer priority
    Integer age = 0                         // TODO think thru the init process!!!
    
    Integer consume(def food){
        starving = false
        food - 1
    }
    
    /** Technically this is an abstract method */ 
    def produce(){}
    
    def reallocate(){}
    
    def multiply(){}
    
    
}
