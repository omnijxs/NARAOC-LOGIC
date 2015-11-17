package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.Preferres
import traits.Produces

/**
 * Created by Juri on 26.10.2015.
 */

class Merchant extends PopUnit implements Consumes, Produces, Preferres {
    
    public Merchant(){
        this.product = Product.TRADE
        this.priority = 2   
    }
}