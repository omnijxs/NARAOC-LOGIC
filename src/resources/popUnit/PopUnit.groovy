package resources.popUnit

import game.GameData
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import resources.common.Priority
import resources.common.Race
import resources.common.Tile
import resources.gameActor.GameActor
import traits.Consumes
import traits.Multiplies
import traits.PopUnitResolver
import traits.Preferres
import traits.Produces
import traits.Reallocates

/**
 * Created by Juri on 21.10.2015.
 */

@CompileStatic
@TypeChecked
class PopUnit implements Consumes,
                         Produces,
                         Preferres,
                         Reallocates,
                         Multiplies,
                         PopUnitResolver {

    @Delegate Race race
    @Delegate Tile tile

    Integer age = 0
    Priority priority
    GameActor owner

    Integer consume(Integer food){
        return consumes(food)
    }

    void produce(GameData gameData){
        if(canProduce(gameData.metadata, this.class.name)){
            produces(race, tile)
        }
    }

    Integer harvest(GameData gameData){
        if(canProduce(gameData.metadata, this.class.name)){
            return harvests()
        }
    }

    void prefer(GameData gameData){
        if(canProduce(gameData.metadata, this.class.name)){
            preferres(gameData.popHubs, tile)
        }
    }

    List<PopUnit> reallocate(GameData gameData, GameActor gameActor, def gameInput){
        if(canReallocate(gameData, gameActor)){
            return reallocates(gameData.popUnits, gameInput)
        }
    }

    PopUnit multiply(GameData gameData){
        if(canMultiply(gameData.metadata, this.class.name, race, starving)){
            return multiplies(multiplicationRate)
        }
    }

    Boolean tax(GameData gameData, GameActor gameActor){
        return canBeTaxed(gameData,gameActor)
    }

}
