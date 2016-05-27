package resources.popHub

import resources.common.Tile
import resources.gameActor.GameActor
import traits.FeedsHub
import traits.Demands

import traits.Refines

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, 
                                     Refines, 
                                     FeedsHub {

    Buildings buildings
    Tile tile
    GameActor owner

}
