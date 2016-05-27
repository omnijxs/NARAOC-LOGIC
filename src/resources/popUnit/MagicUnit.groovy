package resources.popUnit

import resources.common.Priority
import traits.Consumes
import traits.IsOwned
import traits.Reallocates

/**
 * Created by Juri on 06.11.2015.
 */

class MagicUnit extends PopUnit {

    public MagicUnit(){
        this.priority = Priority.MEDIUM
    }
}