package resources.city

import resources.common.Tile
import traits.FeedsCity
import traits.Demands
import traits.IsOwned

/**
 * Created by Juri on 21.10.2015.
 */
class City implements Demands, IsOwned, FeedsCity {

    Tile tile

}
