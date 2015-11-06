package resources.popUnit

import resources.common.Product

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit {
    
    public Worker(){
        this.product = Product.WORK
        this.priority = 2   
    }
}