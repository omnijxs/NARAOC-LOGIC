package resources.popUnit

import game.Player
import resources.city.City
import traits.Consumes

/**
 * Created by Juri on 06.11.2015.
 */

class MagicUnit extends PopUnit implements Consumes{

    public MagicUnit(){
        this.priority = 1
    }
}