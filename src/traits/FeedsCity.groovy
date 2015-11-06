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
        def sortedPopUnits = defaultSort(popUnitsOnCity + popUnitsOffCity)
        
        return feed(sortedPopUnits, foodAmount)

    }

}