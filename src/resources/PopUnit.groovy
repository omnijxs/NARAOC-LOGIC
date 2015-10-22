package resources

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {
    
    Tile tile
    
    Boolean starving
    
    Integer consume(def food){
        
        starving = false
            
        food - 1
    }
    
    /** Technically this is an abstract method */ 
    def produce(){}
    
    def reallocate(){}
    
    def multiply(){}
    
    
}
