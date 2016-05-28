package traits

import resources.common.Product
import resources.popHub.PopHub
import resources.popUnit.PopUnit

/**
 * Created by Juri on 7.11.2015.
 */
trait Demands implements PopUnitFinder {

    Map<Product, Integer> demand = [(Product.FOOD): 0, (Product.WORK): 0, (Product.TRADE) :0]
    
    public void setDemands(List<PopUnit> popUnits, PopHub popHub){

        /** Search for all pop units in the city proper and pop units outside city proper producing for the city. Currently only strategy. */
        def basicDemand = resolveBasicDemand(popUnits, popHub)

        setFoodDemand(basicDemand)
        setWorkDemand(basicDemand)
        setTradeDemand(basicDemand)
    }

    Integer resolveBasicDemand(List<PopUnit> popUnits, PopHub popHub){
        return popHubPopulation(popUnits, popHub).size()
    }

    Integer foodDemand(){
        return demand.get((Product.FOOD))
    }

    Integer workDemand(){
        return demand.get((Product.WORK))
    }

    Integer tradeDemand(){
        return demand.get((Product.TRADE))
    }

    void setFoodDemand(Integer value){
        demand.put((Product.FOOD), value)
    }

    void setWorkDemand(Integer value){
        demand.put((Product.WORK), value)
    }

    void setTradeDemand(Integer value){
        demand.put((Product.TRADE), value)
    }
    
}