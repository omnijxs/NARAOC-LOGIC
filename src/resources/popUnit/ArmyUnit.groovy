package resources.popUnit

import resources.city.City

/**
 * Created by Juri on 22.10.2015.
 */

class ArmyUnit extends PopUnit {

    public ArmyUnit(){
        this.priority = 0
    }

    @Override
    void resolvePreferredCity(List<City> cities){
        preferredCity = null
    }

    
}