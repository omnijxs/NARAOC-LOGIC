package traits

import game.GameData
import resources.popHub.PopHub
import resources.popUnit.PopUnit

/**
 * Created by Juri on 18.11.2015.
 */
trait PopUnitFinder {

    /**
     * The definition of popHubs population
     *
     * @param gd
     * @param popHub
     * @return
     */
    List<PopUnit> popHubPopulation(GameData gd){
        return gd.popUnits.findAll { it.preferredHub == this || it.tile == this.tile }
    }

    /**
     * Finds those populationUnits which are to be fed by the popHub:
     * Find all non-starving population units of the popHub
     *
     * @param gd
     * @param popHub
     * @return
     */
    List<PopUnit> popHubPopulationForFeeding(GameData gd, PopHub popHub){
        return gd.popUnits.findAll { (it.preferredHub == this || it.tile == this.tile) && it.starving }
    }

    // [ ] PopUnitFinder should have two different population search methods: for production and for feeding. Also no popHub parameter required.

}