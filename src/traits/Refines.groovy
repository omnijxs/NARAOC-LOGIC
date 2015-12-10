package traits

import game.GameData
import resources.common.Building
import resources.common.Product
import resources.popHub.PopHubOutput

/**
 * Created by Juri on 20.11.2015.
 */
trait Refines implements PopUnitFinder {

    /** Assumptions: I am implemented by an object which implements PopHub-interface.
     * I need it for access to buildings. And for my population. */

    List<Building> buildings

    PopHubOutput refine(GameData gd){

        PopHubOutput output = new PopHubOutput()

        /** Get all my popUnits. */
        def population = popHubPopulation(gd, this)

        /** Split them according to their product */
        def foodProducers = population.findAll { it.product == Product.FOOD }
        def workProducers = population.findAll { it.product == Product.WORK }
        def tradeProducers = population.findAll { it.product == Product.TRADE }

        /** Harvest their production. */
        foodProducers.each { output.foodProduction += it.harvest() }
        workProducers.each { output.workProduction += it.harvest() }
        tradeProducers.each { output.tradeProduction += it.harvest() }

        /** Run through buildings. */

        output

    }

}