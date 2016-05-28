package traits

import resources.popHub.PopHub
import resources.popHub.PopHubDemand
import resources.popUnit.PopUnit

/**
 * Created by Juri on 7.11.2015.
 */
trait Demands implements PopUnitFinder {

   private List<PopHubDemand> demandData = []
    
    public PopHubDemand setDemands(List<PopUnit> popUnits, PopHub popHub){

        /** Search for all pop units in the city proper and pop units outside city proper producing for the city. Currently only strategy. */
        def basicDemand = resolveBasicDemand(popUnits, popHub)

        PopHubDemand demand = new PopHubDemand(foodDemand: basicDemand, workDemand: basicDemand, tradeDemand: basicDemand)

        return demand
    }

    Integer resolveBasicDemand(List<PopUnit> popUnits, PopHub popHub){
        return popHubPopulation(popUnits, popHub).size()
    }

    def setDemandData(PopHubDemand data){
        demandData.add(data)
    }

    def getDemandData(){
        return demandData.last()
    }

}