package resources.popUnit

import resources.common.Priority
import traits.Consumes
import traits.IsOwned
import traits.Reallocates


/**
 * Created by Juri on 22.10.2015.
 */

class ArmyUnit extends PopUnit implements Consumes,
                                          Reallocates,
                                          IsOwned {

    public ArmyUnit(){
        this.priority = Priority.HIGH
    }

}