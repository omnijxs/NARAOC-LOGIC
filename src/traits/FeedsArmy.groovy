package traits

import game.GameData
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit

/**
 * Created by Juri on 03.11.2015.
 */
trait FeedsArmy implements PopUnitSorter, Feeds {
    
    /** Assumptions: I am implemented by an object which implements GameActor-interface.
      * I need it to find ArmyUnits to feed. */

    Integer feedArmy(GameData gd, Integer foodAmount){

        /** Find armyUnits to feed. Currently we also feed starving armyUnits on cityTiles. */
        def armyUnitsToFeed = gd.popUnits.findAll { it.owner == this && it.starving && it.class == ArmyUnit }

        /** Sort by Pop Unit age. Perhaps some day also by distance. But how to calculate diestance? From what city? */
        def sortedPopUnits = senioritySort(armyUnitsToFeed)

        return feed(sortedPopUnits, foodAmount)
    }

    Integer getSurplusFood(GameData gd){

        Integer foodForArmies = 0

        /** Find cities which produce for me... */
        def loyalHubs = gd.popHubs.findAll { it.owner == this }

        /** Calculate total surplus food. */
        loyalHubs.each { popHub ->
            def turnData = popHub.getTurnData()
            foodForArmies += turnData.surplusFood
        }

        return foodForArmies
    }


}