package traits

import game.GameData
import resources.popHub.PopHub

/**
 * Created by Juri on 23.10.2015.
 */
trait Preferres {

    /** Assumptions: I am implemented by an object which has the property Tile.
      * I need it calculate distances. */
    
    PopHub preferredHub = null

    void preferres(GameData gd){

        /** If there is no city with a preferredValue higher than zero, the Pop Unit does not produce for any city */
        preferredHub = null
        Integer preferredValue = 0

        gd.popHubs.each { c ->

            /** Resolve how far the city is from the Pop Unit*/
            Integer distance = resolveDistance(tile.x, tile.y, c.tile.x, c.tile.y)

            /** The preferredValue is city's demand for the Pop Units' product minus distance to the city */
            Integer tempPreferredValue =  c.demand.get(product) - distance

            if(tempPreferredValue > preferredValue ) {
                preferredHub = c
                preferredValue = tempPreferredValue
            }
                
        }

    }

    /**
     *  Exponential progession (0, 1, 2, 4, 8, 16, 32...) */
    // TODO requires information about map to calculate river bonuses etc.
    // TODO make into an own trait
    Integer resolveDistance(px, py, cx, cy){
        return (Math.abs(cx - px) * Math.abs(cx - px)) + (Math.abs(cy - py) * Math.abs(cy - py))

    }
}