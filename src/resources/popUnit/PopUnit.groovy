package resources.popUnit

import resources.common.Priority

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    @Delegate State state
    Priority priority

    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

}
