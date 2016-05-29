package resources.gameActor

import game.GameData
import resources.popHub.PopHub
import traits.FeedsArmy
import traits.Taxes

/**
 * Created by Juri on 1.12.2015.
 */
class GameActor implements FeedsArmy,
                           Taxes {

    Integer feedArmy(GameData gameData, Integer foodAmount){
        return feedsArmy(gameData.popUnits, this, foodAmount)
    }

    GameActorOutput produce(GameData gameData, GameActor gameActor, Integer surplusFood){
        return gather(gameData, gameActor, surplusFood)
    }

    Integer resolveSurplusFood(List<PopHub> popHubs, GameActor gameActor){
        return getSurplusFood(popHubs, gameActor)
    }

    GameActorOutput getGameActorOutput(){
        return getOutPutData()
    }

    Boolean setGameActorOutput(GameActorOutput data){
        return setOutPutData(data)
    }

   /* GameActorOutput getGameActorOutput(){
        return getOutPutData()
    }

    GameActorOutput getGameActorOutput(GameData gameData, GameActor gameActor, Integer surplusFood){
        resolveGameActorOutput(gameData, gameActor, surplusFood)
    }
*/


}
