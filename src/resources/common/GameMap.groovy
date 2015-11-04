package resources.common

import game.GameData
import resources.city.City
import resources.popUnit.PopUnit

/**
 * Created by Juri on 22.10.2015.
 */
class GameMap {

    GameData gameData

    List<City> cities
    List<Tile> tiles

    List<PopUnit> getPopUnits(){
        return gameData.popUnits
    }

}
