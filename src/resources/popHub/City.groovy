package resources.popHub

import resources.common.Tile
import traits.FeedsHub
import traits.Demands
import traits.IsOwned
import traits.Refines

/**
 * Created by Juri on 21.10.2015.
 */
class City extends PopHub implements Demands, 
                                     Refines, 
                                     FeedsHub, 
                                     IsOwned {

    Buildings buildings
    Tile tile

}
