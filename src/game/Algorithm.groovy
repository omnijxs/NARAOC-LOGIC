package game

import resources.gameActor.GameActor
import resources.popHub.PopHubOutput
import resources.popUnit.PopUnit

/**
 * Created by jxs on 16.12.2015.
 */
class Algorithm {

    protected GameData popUnitsMultiply(GameData gd){

        List<PopUnit> newPopUnits = []

        gd.popUnits.each { popUnit ->
            def a = popUnit.multiply()
            if(a)
                newPopUnits.add(popUnit.multiply())
        }

        gd.popUnits.addAll(newPopUnits)

        return gd
    }

    protected GameData popUnitsProduce(GameData gd){
        gd.popUnits.each { popUnit ->

            /** All pop units calculate to which city they will produce to. */
            popUnit.resolvepreferredHub(gd)

            /** Set production flags up in all popUnits.
             * TileFeeding popUnits feed their tiles and set the surplus as their this turns production. */
            popUnit.produce(gd)
        }

        return gd
    }

    protected GameData popHubsRefine(GameData gd){

        gd.popHubs.each { popHub ->

            /** Calculate bonuses, deal with buildings etc. */
            PopHubOutput output = popHub.refine(gd)

            /** Feed the hub population and calculate the surplus food. */
            output.surplusFood = popHub.feedHub(gd, output.foodProduction)

            /** */
            popHub.setTurnData(output)
        }

        return gd
    }

    /** No tests */
    protected GameData gameActorsSetup(GameData gd){
        gd.gameActors.each { player ->
            /** Lets feed your roaming armies...*/
            Integer surplusFood = feedArmies(gd, player)

            /** Deal with taxation in a separate method. Store the info the player. */
        }

        return gd
    }

    protected Integer feedArmies(GameData gd, GameActor ga){

        Integer foodForArmies = 0

        /** Find cities which produce for me... */
        def obedientHubs = gd.popHubs.findAll { it.owner == ga }

        /** Calculate total surplus food. */
        obedientHubs.each { popHub ->

            def turnData = popHub.getTurnData()

            foodForArmies += turnData.surplusFood
        }

        return ga.feedArmy(gd, foodForArmies)
    }

    /** No tests */
    protected GameData gameActorInput(GameData gd){
        gd.gameActors.each { player ->
            gd = yieldControl(gd, player)
        }

        return gd
    }

    /** No tests */
    protected GameData yieldControl(GameData gd, GameActor a){
        return gd
        /** The actual player/AI input */
    }

    // TODO separate into own methods and create tests for those methods
    protected GameData postProcess(GameData gd){

        /** Calculate demand for pop hubs */
        gd.popHubs.each { popHub ->
            popHub.setDemand(gd)
        }

        /** Deal with pop unit obedience. Note that this MUST be before we set them to starving. */
        gd.popUnits.each { popUnit ->
            // popUnit.dealWithObedience()
        }

        /** Set all PopUnits to starving for next turn. Could be done in pre-process. */
        gd.popUnits.each { popUnit ->
            popUnit.starving = true
        }

        return gd

    }
}
