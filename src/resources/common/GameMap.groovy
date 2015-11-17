package resources.common

import game.GameData
import resources.popHub.PopHub

/**
 * Created by Juri on 22.10.2015.
 */
class GameMap {

    GameData gameData   // TODO do I need this dependency anymore?

    List<PopHub> cities // TODO rename hubs
    List<Tile> tiles

}
