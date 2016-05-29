package traits

import game.GameData
import resources.gameActor.GameActor
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit

/**
 * Created by Juri on 03.11.2015.
 */
trait FeedsArmy implements PopUnitSorter, Feeds {

    Integer feedsArmy(List<PopUnit> popUnits, GameActor gameActor, Integer foodAmount){

        /** Find armyUnits to feed. Currently we also feed starving armyUnits on cityTile. */
        def armyUnitsToFeed = popUnits.findAll { it?.owner == gameActor && it?.starving && it?.class == ArmyUnit }

        /** Sort by Pop Unit age. Perhaps some day also by distance. But how to calculate diestance? From what city? */
        def sortedPopUnits = senioritySort(armyUnitsToFeed)

        return feed(sortedPopUnits, foodAmount)
    }

    // TODO RELOCATE!!!
/*    Integer getSurplusFood(GameData gd){

        Integer foodForArmies = 0

        *//** Find cities which produce for me... *//*
        def loyalHubs = gd.popHubs.findAll { it.owner == this }

        *//** Calculate total surplus food. *//*
        loyalHubs.each { popHub ->
            def turnData = popHub.getTurnData()
            foodForArmies += turnData.surplusFood
        }

        return foodForArmies
    }*/


}