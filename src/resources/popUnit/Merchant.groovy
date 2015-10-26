package resources.popUnit

import resources.common.Product

/**
 * Created by Juri on 26.10.2015.
 */

class Merchant extends PopUnit {
    
    public Merchant(){
        this.product = Product.TRADE
        this.age = 0
        this.priority = 2   
    }
}