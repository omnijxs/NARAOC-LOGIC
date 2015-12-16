package traits

import game.GameData
import resources.gameActor.GameActorOutput

/**
 * Created by jxs on 16.12.2015.
 */
trait Taxes {

    private List<GameActorOutput> turnData = []

    Integer foodTaxRate = 0
    Integer workTaxRate = 0
    Integer tradeTaxRate = 0

    def tax(GameData gd, GameActorOutput output){

    }

    GameActorOutput getTotalOutput(GameData gd){
        GameActorOutput output = new GameActorOutput()

        /** Find cities which produce for me... */
        def loyalHubs = gd.popHubs.findAll { it.owner == this }

        /** Add all their production */
        loyalHubs.each { popHub ->
            output.foodTotal += popHub.getTurnData().foodProduction
            output.workTotal += popHub.getTurnData().workProduction
            output.tradeTotal += popHub.getTurnData().tradeProduction
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