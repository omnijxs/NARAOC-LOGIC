package resources.popUnit

import resources.common.Tile

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    @Delegate State state 

    // TODO does this work this State
    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

}
