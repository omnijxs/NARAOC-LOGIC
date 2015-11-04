package resources.popUnit

import game.Player
import resources.city.City

/**
 * Created by Juri on 22.10.2015.
 */

class ArmyUnit extends PopUnit {

    public ArmyUnit(){
        this.age = 0
        this.priority = 0
    }

    @Override
    void resolvePreferredCity(List<City> cities){
        preferredCity = null
    }

    
}