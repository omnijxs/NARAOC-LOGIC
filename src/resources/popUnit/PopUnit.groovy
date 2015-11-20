package resources.popUnit

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit {

    @Delegate State state
    Integer priority            // TODO this should not be here

    // TODO does this work this State
    def methodMissing(String name, args) {
        null
    }

    def propertyMissing(String name){
        null
    }

}
