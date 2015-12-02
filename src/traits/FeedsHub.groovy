package traits

import game.GameData

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsHub implements PopUnitSorter, PopUnitFinder, Feeds {

    Integer feedHub(GameData gd, Integer foodAmount){

        /** Assumes this is an implementation of a popHub */
        def popUnits = popHubPopulationStarving(gd, this)

        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = productionSort(popUnits)
        
        return feed(sortedPopUnits, foodAmount)

    }

}