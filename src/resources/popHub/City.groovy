package resources.popHub

import game.GameData
import resources.common.Tile
import traits.FeedsCity
import traits.Demands
import traits.IsOwned

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, Refines, IsOwned, FeedsCity {

    Tile tile

}
