package traits

import resources.common.Product
import game.GameData

/**
 * Created by Juri on 16.11.2015.
 */
trait Produces {
    
    Product product
    Integer productAmount = 0
    Integer harvestAmount = 0
    
    Integer produces(GameData gd){
        harvestAmount = productAmount.intValue()
    }
    
    Integer harvests(){
        Integer temp = harvestAmount.intValue()
		harvestAmount = 0
		temp
    }

}