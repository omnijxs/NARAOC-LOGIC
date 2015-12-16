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
        def population = popHubProductionPopulation(gd, this)

        /** Split them according to their product */
        def foodProducers = population.findAll { it.product == Product.FOOD }
        def workProducers = population.findAll { it.product == Product.WORK }
        def tradeProducers = population.findAll { it.product == Product.TRADE }

        /** Harvest their production and get possible bonuses from buildings. */
        foodProducers.each {
            output.foodProduction += buildings.resolveFoodBonus(it.harvest())
        }

        workProducers.each {
            output.workProduction += buildings.resolveWorkBonus(it.harvest())
        }

        tradeProducers.each {
            output.tradeProduction += buildings.resolveTradeBonus(it.harvest())
        }

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