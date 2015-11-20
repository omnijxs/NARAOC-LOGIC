package resources.popUnit

import traits.Consumes
import traits.IsOwned

/**
 * Created by Juri on 06.11.2015.
 */

class MagicUnit extends PopUnit implements Consumes, IsOwned {

    public MagicUnit(){
        this.priority = 1
        state = new State()
    }
}