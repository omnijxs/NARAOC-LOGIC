package resources.popUnit

import traits.Consumes
import traits.IsOwned


/**
 * Created by Juri on 22.10.2015.
 */

class ArmyUnit extends PopUnit implements Consumes, IsOwned {

    public ArmyUnit(){
        this.priority = 0
        state = new State()
    }

}