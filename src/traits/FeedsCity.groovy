package traits

import game.GameData

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter, PopUnitFinder, Feeds {

    Integer feedCity(GameData gd, Integer foodAmount){

        /** Assumes this is an implementation of a popHub */
        def popUnits = popHubPopulationStarving(gd, this)

        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = productionSort(popUnits)
        
        return feed(sortedPopUnits, foodAmount)

    }
    
    /** Don’t feed farmers or units which have been already fed. FeedCity method assumes it gets the popUnits of the city as a list 
        So it does not search them. */
	// def popUnitsToFeed = popUnits.findAll { it.product != Produce.FOOD && it.starving }

}