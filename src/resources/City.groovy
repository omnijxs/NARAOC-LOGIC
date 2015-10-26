package resources

/**
 * Created by Juri on 21.10.2015.
 */
class City {

    Tile tile
    Integer demandForFood
    
    Map<Product, Integer> demand
    
    public City(){
     
        // TODO How can use enum FOOD instead of Product.FOOD?
        // TODO Consider using a custom Demand object?
        demand.put(Product.FOOD, 0)
        demand.put(Product.WORK, 0)
        demand.put(Product.TRADE, 0)
        
    }

}
