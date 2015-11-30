package resources.popHub

import resources.common.Tile
import traits.FeedsCity
import traits.Demands
import traits.IsOwned
import traits.Refines

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, Refines, IsOwned, FeedsCity {

    Tile tile

}
