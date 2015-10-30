package traits

/**
 * Created by Juri on 29.10.2015.
 */
trait Feeds {

    Integer feed(def popUnits, def foodAmount){
        
        // TODO make ignorant if the popUnits parameter is an object or a list
        popUnits.each {
            if(foodAmount)
                foodAmount = it.consume(foodAmount)
        }

        foodAmount

    }

}