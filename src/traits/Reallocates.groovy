package traits

import game.GameData
import resources.popUnit.PopUnit

/**
 * Created by Juri on 04.12.2015.
 */
trait Reallocates {

    /** Assumptions: I am implemented by an object which implements PopUnit-interface.
      * I need it to remove myself from popUnits-list. */
    
    /** gameInput.reallocator   /** Who reallocated me? Usually a popHub. Needed for tile info and possible bonuses. */
    /** gameInput.popUnitClass  /** The class of the new PopUnit */
    /** gameInput.popUnitType   /** For armyUnit and magicUnit type identification */
    /** gameInput.gameActor     /** Which gameActor reallocated me. Needed for obedience calculations. */

    def reallocates(GameData gd, def gameInput){
        
        /** Resolve can the player actually reallocate this pop unit */
        if(canReallocate(gd, gameInput.gameActor)){
            /** Create the new pop unit */
            PopUnit reallocated = createNewInstance(gameInput)

            /** Resolve its properties */
            reallocated = resolveState(reallocated)

            /** Add it to gameData */
            gd.popUnits.add(reallocated)

            /** Remove the old pop unit from the game data*/
            return manipulateGameData(gd)
        } else
            return gd       /** Should also return error info to UI */

    }

    /**
     * Create the new PopUnit
     *
     * @param gameInput
     * @return
     */
    PopUnit createNewInstance(def gameInput){

        GroovyClassLoader c = new GroovyClassLoader()

        def newClass = c.loadClass(gameInput.popUnitClass)

        return newClass.newInstance()

    }

    PopUnit resolveState(def reallocated){
        /** Deal with obedience */
        /** Calculate new tile */
        return reallocated
    }


    /** Is the game data manipulation done on this level? or higher? */
    /** Put into correct place after architectural and data flow decisions */
    GameData manipulateGameData(GameData gd){

        gd.popUnits.remove(this)

        return gd
    }

}