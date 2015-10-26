package resources

/**
 * Created by Juri on 26.10.2015.
 */

enum Product {
    
    FOOD("Food"),
    WORK("Work"),
    TRADE("Trade)   
    
    final String label
    
    private Product(String label){
        this.label = label
        
    }
  
}