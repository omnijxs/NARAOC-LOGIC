package resources.popUnit

import resources.common.Priority
import resources.common.Product
import traits.Consumes
import traits.Multiplies
import traits.Preferres
import traits.Produces
import traits.Reallocates

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit {
    
    public Worker(){
        this.product = Product.WORK
        this.productAmount = 1 /** Should be from a config file */
        this.priority = Priority.LOW
    }
}