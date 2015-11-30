package game

import resources.popHub.City
import resources.common.GameMap
import resources.popUnit.PopUnit

/**
 * Created by Juri on 21.10.2015.
 */
class GameData {

    Metadata metadata

    // @Delegate GameMap gameMap
    
    List<PopUnit> popUnits
    List<Player> players
    List<PopHub> hubs
    List<Tile> tiles

}
