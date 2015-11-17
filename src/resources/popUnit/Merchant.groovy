package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.Preferred

/**
 * Created by Juri on 26.10.2015.
 */

class Merchant extends PopUnit implements Preferred, Consumes{
    
    public Merchant(){
        this.product = Product.TRADE
        this.priority = 2   
    }
}