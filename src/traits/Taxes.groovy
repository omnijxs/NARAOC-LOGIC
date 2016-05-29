package traits

import game.GameData
import resources.gameActor.GameActor
import resources.gameActor.GameActorOutput
import resources.popHub.PopHub
import resources.popHub.PopHubOutput
import resources.popUnit.PopUnit

/**
 * Created by jxs on 16.12.2015.
 */
trait Taxes {

    private List<GameActorOutput> outPutData = []

    Integer foodTaxRate = 0
    Integer workTaxRate = 0
    Integer tradeTaxRate = 0

    Integer tax(){

       /* def data = getTurnData()

        data.foodTaxOutput = data.foodTotal / 100 * foodTaxRate
        data.workTaxOutput = data.workTotal / 100 * workTaxRate
        data.tradeTaxOutput = data.tradeTotal / 100 * tradeTaxRate

        data.totalTaxAmount = data.foodTaxOutput + data.workTaxOutput + data.tradeTaxOutput

        return data.totalTaxAmount*/

    }

    /** An own trait, perhaps? */
    GameActorOutput gather(GameData gameData, GameActor gameActor){
        GameActorOutput output = new GameActorOutput()

        /** Find cities which produce for me... */
        def loyalHubs = gameData.popHubs.findAll { it?.owner == gameActor }

        /** Add all their production */
        loyalHubs.each { PopHub popHub ->

            PopHubOutput popHubOutput = popHub.getPopHubOutput()

            /** Iterate through all food producers and check if they can be taxed */
            popHubOutput?.food?.each { k, v ->
                if(k.tax(gameData, gameActor)){
                    output.foodTotal += v
                }
            }

            /** Iterate through all work producers and check if they can be taxed */
            popHubOutput?.work?.each { k, v ->
                if(k.tax(gameData, gameActor)){
                    output.workTotal += v
                }
            }

            /** Iterate through all trade producers and check if they can be taxed */
            popHubOutput?.trade?.each { k, v ->
                if(k.tax(gameData, gameActor)){
                    output.tradeTotal += v
                }
            }

        }

        return output
    }

    Boolean setOutPutData(GameActorOutput data){
        outPutData.add(data)
    }

    GameActorOutput getOutPutData(){
        return outPutData.last()
    }

}