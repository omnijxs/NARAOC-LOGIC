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
        this.priority = 2   
    }
}