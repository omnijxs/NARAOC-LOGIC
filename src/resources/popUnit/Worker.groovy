package resources.popUnit

import resources.common.Product
import traits.Consumes
import traits.Preferred

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit implements Preferred, Consumes {
    
    public Worker(){
        this.product = Product.WORK
        this.priority = 2   
    }
}