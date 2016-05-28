package resources.popHub

import resources.common.Tile
import resources.gameActor.GameActor
import traits.Demands
import traits.FeedsHub
import traits.Refines

/**
 * Created by Juri on 17.11.2015.
 */
class PopHub implements Demands,
                        Refines,
                        FeedsHub {

    Tile tile
    GameActor owner

}
