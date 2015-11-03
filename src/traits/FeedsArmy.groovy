package traits

import resources.popUnit.ArmyUnit

/**
 * Created by Juri on 03.11.2015.
 */
trait FeedsArmy implements PopUnitSorter, Feeds {

    Integer feedArmy(Integer foodAmount){

        // TODO FeedsArym has no access to TILE!!! Add owner to PopUnit AND starving check is not fully robust!!!
        def armyUnitsToFeed = tile.map.game.popUnits.findAll { it.owner == player && it.starving }

        /** Sort by Pop Unit age */
        def sortedPopUnits = defaultSort(armyUnitsToFeed)
        
        return feed(sortedPopUnits, foodAmount)

    }

}