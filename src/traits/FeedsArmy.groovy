package traits

import game.GameData
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit

/**
 * Created by Juri on 03.11.2015.
 */
trait FeedsArmy implements PopUnitSorter, Feeds {

    Integer feedArmy(GameData gd, Integer foodAmount){

        /** Assumes that Player object implements me!!! */
        def armyUnitsToFeed = gd.popUnits.findAll { it.owner == this && it.starving && it.class == ArmyUnit}

        /** Sort by Pop Unit age */
        def sortedPopUnits = defaultSort(armyUnitsToFeed)
        
        return feed(sortedPopUnits, foodAmount)

    }

}