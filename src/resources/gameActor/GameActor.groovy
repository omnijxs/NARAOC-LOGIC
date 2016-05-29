package resources.gameActor

import game.GameData
import traits.FeedsArmy
import traits.Taxes

/**
 * Created by Juri on 1.12.2015.
 */
class GameActor implements FeedsArmy/*,
                           Taxes*/ {

    Integer feedArmy(GameData gameData, Integer foodAmount){
        return feedsArmy(gameData.popUnits, this, foodAmount)
    }

}
