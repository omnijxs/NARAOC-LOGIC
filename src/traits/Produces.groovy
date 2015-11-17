package traits

import resources.common.Product

/**
 * Created by Juri on 16.11.2015.
 */
trait Produces {
    
    Product product
    Integer productAmount = 0
    Integer harvestAmount = 0
    
    Integer produce(){
        harvestAmount = productAmount.intValue()
    }
    
    Integer harvest(){
        Integer temp = harvestAmount.intValue()
		harvestAmount = 0
		temp
    }

}