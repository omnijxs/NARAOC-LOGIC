package resources.popUnit

import resources.common.Priority
import resources.common.Product
import game.GameData
import traits.Consumes
import traits.FeedsTile
import traits.Multiplies
import traits.Preferres
import traits.Produces
import traits.Reallocates

/**
 * Created by Juri on 21.10.2015.
 */
class Farmer extends PopUnit implements FeedsTile  {
    
    public Farmer(){
        this.product = Product.FOOD
        this.productAmount = 2 /** Should be from a config file */ 
        this.priority = Priority.LOW
    }
    
    @Override
    Integer produce(GameData gd){
        harvestAmount = feedTile(gd, productAmount)
    }

}