package resources.popUnit

import resources.common.Product
import game.GameData
import traits.Consumes
import traits.FeedsTile
import traits.Preferres
import traits.Produces

/**
 * Created by Juri on 21.10.2015.
 */
class Farmer extends PopUnit implements Consumes, Produces, Preferres, FeedsTile  {
    
    public Farmer(){
        this.product = Product.FOOD
        this.productAmount = 2 /** Should be from a config file */ 
        this.priority = 2
        this.state = new State()
    }
    
    @Override
    Integer produce(GameData gd){
        return feedTile(gd, productAmount)
    }

}