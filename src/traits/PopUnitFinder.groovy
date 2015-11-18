package traits

import game.GameData
import resources.popHub.PopHub
import resources.popUnit.PopUnit

/**
 * Created by Juri on 18.11.2015.
 */
trait PopUnitFinder {

    List<PopUnit> popHubPopulation(GameData gd, PopHub popHub){
        return gd.popUnits.findAll { it.preferredCity == popHub || it.tile == popHub.tile }
    }

}