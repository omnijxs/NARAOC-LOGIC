package traits

import game.GameData

/**
 * Created by Juri on 7.11.2015.
 */
trait Demand {

     // TODO not the optimal way to bind production of a pop unit and city demand to together!
    Map<Product, Integer> demand = ['Product.FOOD': 0, 'Product.WORK': 0, 'Product.TRADE':0]

    // TODO the problem with not knowing how city alters the demand also. Override? 
    void setDemand(GameData gd){
    
        // THE SIMPLEST IMPLEMENTATION
        def basicDemand = gd.popUnits.findAll { it.preferredCity == this }.size()
        
        demand.Product.FOOD = basicDemand
        demand.Product.WORK = basicDemand
        demand.Product.TRADE = basicDemand
        
    }
    
}