package resources

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