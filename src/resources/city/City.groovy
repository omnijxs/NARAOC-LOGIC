package resources.city

import resources.common.Product
import resources.common.Tile
import traits.FeedsCity

/**
 * Created by Juri on 21.10.2015.
 */
class City implements FeedsCity {

    Tile tile

    // TODO not the optimal way to bind production of a pop unit and city demand to together!
    Map<Product, Integer> demand = ['Product.FOOD': 0, 'Product.WORK': 0, 'Product.TRADE':0]

}
