package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter, Feeds {

    Integer feedCity(Integer value){

        /** Get all popUnits on the city tile  */
        def popUnitsOnCityTile = tile.popUnitsOnTile()
        
        // TODO deal with units producing for the city outside city proper!!!       
        
        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = priorityProductionSortPopUnits(popUnitsOnCityTile)
        
        return feed(sortedPopUnits, value)

    }

}