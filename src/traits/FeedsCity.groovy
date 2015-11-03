package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 22.10.2015.
 */
trait FeedsCity implements PopUnitSorter, Feeds {

    Integer feedCity(Integer foodAmount){

        /** Get all popUnits on the city tile  */
        def popUnitsOnCity = tile.popUnitsOnTile()
        
        /** Get all popUnits producing for the city */
        // TODO logically should filter out ARmyUnit and MagicUnit out! Now they are not included because they can't have a preferred city
        def popUnitsOffCity = tile.map.game.popUnits.findAll { it.preferredCity == this && it.tile != tile }

        /** Sort by Pop Unit type, production value and age */
        def sortedPopUnits = defaultSort(popUnitsOnCity + popUnitsOffCity)
        
        return feed(sortedPopUnits, foodAmount)

    }

}