package traits

import game.GameData
import resources.common.Building
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

        /** Harvest their production. */
        population.each { popUnit ->

            // TODO the best way to identify different production types?

        }

        /** Run through buildings. */

        output


    }

}