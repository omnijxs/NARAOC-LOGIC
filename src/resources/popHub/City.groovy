package resources.popHub

import resources.common.Tile
import traits.FeedsCity
import traits.Demands
import traits.IsOwned

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, IsOwned, FeedsCity {

    Tile tile

}
