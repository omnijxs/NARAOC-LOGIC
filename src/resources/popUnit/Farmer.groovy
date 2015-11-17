package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.FeedsTile
import traits.Preferred

/**
 * Created by Juri on 21.10.2015.
 */

class Farmer extends PopUnit implements FeedsTile, Preferred, Consumes {
    
    public Farmer(){
        this.product = Product.FOOD
        this.priority = 2   
    }
    
    @Override
    def produce(){
        // return feedTile(2) >= 0 ? feedTile(2) : 0 unnecessary null check
        return feedTile(2)
    }

}