package game

import resources.common.GameMap
import resources.popUnit.PopUnit

/**
 * Created by Juri on 21.10.2015.
 */
class GameData {

    Metadata metadata

    GameMap gameMap
    List<PopUnit> popUnits
    List<Player> players

    // TODO For some reason cannot use @Delegate GameMap gameMap. Cyclical reference?
    public List<PopUnit> getCities(){
        return gameMap.cities
    }

    public List<PopUnit> getTiles(){
        return gameMap.tiles
    }

}
