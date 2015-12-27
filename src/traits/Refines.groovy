package traits

import game.GameData
import resources.common.Product
import resources.popHub.PopHubOutput

/**
 * Created by Juri on 20.11.2015.
 */
trait Refines implements PopUnitFinder {

    private List<PopHubOutput> turnData = []

    /** Assumptions: I am implemented by an object which implements PopHub-interface.
     * I need it for access to buildings. And for my population. */

    PopHubOutput refine(GameData gd){

        PopHubOutput output = new PopHubOutput()

        /** Get all my popUnits. */
        def population = popHubPopulationProducing(gd, this)

        /** Split them according to their product */
        def foodProducers = population.findAll { it.product == Product.FOOD }
        def workProducers = population.findAll { it.product == Product.WORK }
        def tradeProducers = population.findAll { it.product == Product.TRADE }

        /** Harvest their production and get possible bonuses from buildings. */
        foodProducers.each { p ->
            output.food.put(p, buildings.resolveFoodBonus(p.harvest()))
        }

        workProducers.each { p ->
            output.work.put(p, buildings.resolveWorkBonus(p.harvest()))

        }

        tradeProducers.each { p ->
            output.trade.put(p, buildings.resolveTradeBonus(p.harvest()))
        }

        // TODO deal with production for buildings

        output
    }

    def setTurnData(PopHubOutput data){
        turnData.add(data)
    }

    def getTurnData(){
        /** We now assume that list.add() always adds to the end of list */
        return turnData.last()
    }

}