package traits

/**
 * Created by Juri on 16.11.2015.
 */
trait Consumes {
    
    Boolean starving = true 
    
    Integer consume(def food){
        starving = false
        food - 1
    }

}