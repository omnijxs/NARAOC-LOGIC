package resources.popUnit

import resources.common.Priority
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
        this.productAmount = 1 /** Should be from a config file */
        this.priority = Priority.LOW
        this.state = new State()
    }
}