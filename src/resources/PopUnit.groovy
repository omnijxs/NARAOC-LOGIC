package resources

import traits.Preferred

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit implements Preferred {
    
    Tile tile
    Product product             
    
    Boolean starving
    Integer priority
    Integer age
    
    Integer consume(def food){
        starving = false
        food - 1
    }
    
    /** Technically this is an abstract method */ 
    def produce(){}
    
    def reallocate(){}
    
    def multiply(){}
    
    
}
