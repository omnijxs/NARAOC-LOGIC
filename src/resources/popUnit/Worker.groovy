package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.Preferred
import traits.Produces

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit implements Consumes, Produces, Preferred {
    
    public Worker(){
        this.product = Product.WORK
        this.priority = 2   
    }
}