package traits

import game.GameData

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter, Feeds {

    Integer feedCity(GameData gd, Integer foodAmount){

        /** Get all popUnits on the city tile  */
        def popUnitsOnCity = gd.popUnits.findAll { it.tile == tile }
        
        /** Get all popUnits producing for the city */
        def popUnitsOffCity = gd.popUnits.findAll { it.preferredCity == this && it.tile != tile }

        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = productionSort(popUnitsOnCity + popUnitsOffCity)
        
        return feed(sortedPopUnits, foodAmount)

    }
    
    /** Don’t feed farmers or units which have been already fed. FeedCity method assumes it gets the popUnits of the city as a list 
        So it does not search them. */
	// def popUnitsToFeed = popUnits.findAll { it.product != Produce.FOOD && it.starving }

}