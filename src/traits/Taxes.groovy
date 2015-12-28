package traits

import game.GameData
import resources.gameActor.GameActorOutput
import resources.popUnit.PopUnit

/**
 * Created by jxs on 16.12.2015.
 */
trait Taxes {

    private List<GameActorOutput> turnData = []

    Integer foodTaxRate = 0
    Integer workTaxRate = 0
    Integer tradeTaxRate = 0

    Integer tax(){

        def data = getTurnData()

        data.foodTaxOutput = data.foodTotal / 100 * foodTaxRate
        data.workTaxOutput = data.workTotal / 100 * workTaxRate
        data.tradeTaxOutput = data.tradeTotal / 100 * tradeTaxRate

        data.totalTaxAmount = data.foodTaxOutput + data.workTaxOutput + data.tradeTaxOutput

        return data.totalTaxAmount

    }

    GameActorOutput getTotalOutput(GameData gd){
        GameActorOutput output = new GameActorOutput()

        /** Find cities which produce for me... */
        def loyalHubs = gd.popHubs.findAll { it.owner == this }

        /** Add all their production */
        loyalHubs.each { popHub ->

            /** Iterate through all food producers and check if they can be taxed */
            popHub.getTurnData().food.each { k, v ->
                if(k.canBeTaxed(gd, this)){
                    output.foodTotal += v
                }
            }

            /** Iterate through all work producers and check if they can be taxed */
            popHub.getTurnData().work.each { k, v ->
                if(k.canBeTaxed(gd, this)){
                    output.workTotal += v
                }
            }

            /** Iterate through all trade producers and check if they can be taxed */
            popHub.getTurnData().trade.each { k, v ->
                if(k.canBeTaxed(gd, this)){
                    output.tradeTotal += v
                }
            }

        }

        return output
    }

    def setTurnData(GameActorOutput data){
        turnData.add(data)
    }

    def getTurnData(){
        /** We now assume that list.add() always adds to the end of list */
        return turnData.last()
    }

}