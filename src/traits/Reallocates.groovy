package traits

import game.GameData
import resources.popUnit.PopUnit

/**
 * Created by Juri on 04.12.2015.
 */
trait Reallocates {

    /** gameInput.reallocator   /** Who reallocated me? Usually a popHub. Needed for tile info and possible bonuses. */
    /** gameInput.popUnitClass  /** The class of the new PopUnit */
    /** gameInput.popUnitType   /** For armyUnit and magicUnit type identification */

    def reallocate(GameData gd, def gameInput){

        PopUnit reallocated = reallocate(gameInput)

        reallocated = resolveState(reallocated)

        gd.popUnits.add(reallocated)

        return manipulateGameData(gd)

    }

    /**
     *
     * Create the new PopUnit
     *
     * @param gameInput
     * @return
     */

    PopUnit reallocate(def gameInput){

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