package resources.popUnit

import game.GameData
import resources.common.Priority
import resources.common.Product
import resources.common.Race
import resources.common.Tile
import resources.gameActor.GameActor
import resources.popUnit.obedience.Obedience
import traits.Consumes
import traits.Multiplies
import traits.Preferres
import traits.Produces
import traits.Reallocates

/**
 * Created by Juri on 21.10.2015.
 */
class PopUnit implements Consumes, Produces, Preferres, Reallocates, Multiplies {

    @Delegate Obedience obedience
    @Delegate Race race
    @Delegate Tile tile

    Integer age = 0
    Priority priority

    def consume(Integer food){
        consumes(food)
    }

    def produce(GameData gameData){
        produces(gameData)
    }

    def prefer(GameData gameData){
        preferres(gameData)
    }

    def reallocate(GameData gameData, def gameInput){
        reallocates(gameData, gameInput)
    }

    def multiply(){
        multiplies(race.multiplicationRate)
    }

    public Boolean canMultiply(){
        return !starving
    }
    
    public Boolean canReallocate(GameData gd, GameActor ga){
        return isObedient(gd, ga)
    }
    
    public Boolean canBeTaxed(GameData gd, GameActor ga){
        return isObedient(gd, ga)
    }
    
    private Boolean isObedient(GameData gd, GameActor ga){
        return resolveObedience(gd, ga) > 0
    }


}
