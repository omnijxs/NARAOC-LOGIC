package resources.popUnit

import resources.common.Product
import traits.Preferred

/**
 * Created by Juri on 26.10.2015.
 */

class Worker extends PopUnit implements Preferred  {
    
    public Worker(){
        this.product = Product.WORK
        this.priority = 2   
    }
}