package resources.popHub

import game.GameData
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import resources.common.Tile
import resources.gameActor.GameActor
import traits.Demands
import traits.FeedsHub
import traits.Refines

/**
 * Created by Juri on 17.11.2015.
 */
@CompileStatic
@TypeChecked
class PopHub implements Demands,
                        Refines,
                        FeedsHub {

    @Delegate Tile tile
    @Delegate GameActor owner

    PopHubDemand setDemand(GameData gameData){
        setDemands(gameData.popUnits, this)
    }

    Integer feedHub(GameData gameData, Integer foodAmount){
        return feedsHub(gameData.popUnits, this, foodAmount)
    }


}
