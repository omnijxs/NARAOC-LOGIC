package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.Preferres
import traits.Produces

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit implements Consumes, Produces, Preferres {
    
    public Worker(){
        this.product = Product.WORK
        this.productAmount = 1 /** Should be from a config file */ 
        this.priority = 2
        this.state = new State()
    }
}