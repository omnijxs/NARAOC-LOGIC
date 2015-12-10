package resources.popHub

import resources.common.Buildings
import resources.common.Tile
import traits.FeedsHub
import traits.Demands
import traits.IsOwned
import traits.Refines

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, Refines, IsOwned, FeedsHub {

    Buildings buildings
    Tile tile

}
