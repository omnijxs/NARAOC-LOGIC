package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter {

    Integer feedCity(Integer value){

        /** Get all popUnits on the city tile  */
        def popUnitsOnCityTile = tile.popUnitsOnTile()
        
        // TODO deal with units producing for the city outside city proper!!!       
        
        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = priorityProductionSortPopUnits(popUnitsOnCityTile)
        
        // TODO Make into a trait. Used also in FeedsTile.
        sortedPopUnits.each {
            if(value)
                value = it.consume(value)
        }

        value

    }

}