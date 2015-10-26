package traits

import resources.city.City
import resources.common.Product

/**
 * Created by Juri on 23.10.2015.
 */
trait Preferred {

    City preferredCity = null

    void resolvePreferredCity(List<City> cities){

        Map<City, Integer> unsortedResults = [:]
        Map<City, Integer> results = [:]

        cities.each { c ->

            def distance = resolveDistance(tile.x, tile.y, c.tile.x, c.tile.y)

            def preferredValue =  c.demand.get(product) - distance

            if(preferredValue > 0)
                unsortedResults.put(c, preferredValue)
        }

        if(unsortedResults){
            results = unsortedResults.sort { -it.value }
            preferredCity = results?.keySet()?.first()
        } else {
            preferredCity = null
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