package traits

import game.GameData
import resources.common.Product

/**
 * Created by Juri on 7.11.2015.
 */
trait Demands {

     // TODO not the optimal way to bind production of a pop unit and city demand to together!
    Map<Product, Integer> demand = ['Product.FOOD': 0, 'Product.WORK': 0, 'Product.TRADE':0]
    
    // TODO the problem with not knowing how city alters the demand also. Return back to City Object?
    void setDemand(GameData gd){

        /** Search for all pop units in the city proper and pop units outside city proper producing for the city */
        def basicDemand = gd.popUnits.findAll { (it.tile == tile) || (it.preferredCity == this && it.tile != tile) }.size()

        // TODO AWFUL SYNTAX!!!
        demand.put(Product.FOOD, basicDemand)
        demand.put(Product.WORK, basicDemand)
        demand.put(Product.TRADE, basicDemand)

        
    }
    
}