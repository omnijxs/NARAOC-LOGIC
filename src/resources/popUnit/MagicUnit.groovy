package resources.popUnit

import game.Player
import resources.city.City

/**
 * Created by Juri on 06.11.2015.
 */

class MagicUnit extends PopUnit {

    public MagicUnit(){
        this.age = 0
        this.priority = 1
    }

    @Override
    void resolvePreferredCity(List<City> cities){
        preferredCity = null
    }
}