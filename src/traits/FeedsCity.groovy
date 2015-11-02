package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter, Feeds {

    Integer feedCity(Integer foodAmount){

        /** Get all popUnits on the city tile  */
        def popUnitsOnCity = tile.popUnitsOnTile()
        
        // TODO deal with units producing for the city outside city proper!!!
        // def popUnitsOffCity = tile.map.game.popUnits.findAll { it.tile != this && it.preferredCity == }
        
        /** Sort by Pop Unit type, production value and age */
        // def sortedPopUnits = productionSort(popUnitsOnCity)
        def sortedPopUnits = defaultSort(popUnitsOnCity)
        
        return feed(sortedPopUnits, foodAmount)

    }

}