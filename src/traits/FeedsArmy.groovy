package traits

import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit

/**
 * Created by Juri on 03.11.2015.
 */
trait FeedsArmy implements PopUnitSorter, Feeds {

    Integer feedArmy(List<PopUnit> popUnits, Integer foodAmount){

        // FeedsArmy has no access to TILE!!!
        // def armyUnitsToFeed = tile.map.game.popUnits.findAll { it.owner == player && it.starving }

        // Assumes that Player object implements me!!!
        def armyUnitsToFeed = popUnits.findAll { it.owner == this && it.starving && it.class == ArmyUnit}

        /** Sort by Pop Unit age */
        def sortedPopUnits = defaultSort(armyUnitsToFeed)
        
        return feed(sortedPopUnits, foodAmount)

    }

}