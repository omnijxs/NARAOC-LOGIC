package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.Buildings
import resources.common.Race
import resources.popHub.City
import resources.common.Tile
import resources.popHub.PopHubOutput
import resources.popHub.PopHub
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.popUnit.State
import resources.popUnit.Worker

/**
 * Created by Juri on 16.11.2015.
 */
class UseCaseTest {

    protected Map<PopHub, PopHubOutput> turnData     // TODO RENAME
    protected GameData gameData
    protected PopHub city
    protected PopUnit farmer
    protected PopUnit worker
    protected PopUnit merchant
    protected Tile cityTile

    @Before
    void setUp() {
        gameData = new GameData()

        cityTile = new Tile(x: 1, y: 1)

        city = new City(tile: cityTile, buildings: new Buildings())

        gameData.popHubs = [city]

        farmer = new Farmer(state: new State(tile: cityTile, race: new Race()))
        worker = new Worker(state: new State(tile: cityTile, race: new Race()))
        merchant = new Merchant(state: new State(tile: cityTile, race: new Race()))

        gameData.popUnits = [farmer, worker, merchant]

        turnData = [:]

    }

    protected GameData popUnitsMultiply(GameData gameData){

        List<PopUnit> newPopUnits = []

        gameData.popUnits.each { popUnit ->
            def a = popUnit.multiply()
            if(a)
                newPopUnits.add(popUnit.multiply())
        }

        gameData.popUnits.addAll(newPopUnits)

        return gameData
    }

    protected GameData popUnitsProduce(GameData gameData){
        gameData.popUnits.each { popUnit ->
            popUnit.resolvepreferredHub(gameData)

            /** TileFeeding popUnits feed their tiles and set the surplus as their this turns production.
             Also set the production “flags” up to their popUnits */
            popUnit.produce(gameData)
        }

        return gameData
    }

    protected GameData popHubsRefine(GameData gameData){
        gameData.popHubs.each { popHub ->

            /** Calculate bonuses, deal with buildings etc. */
            PopHubOutput output = popHub.refine(gameData)

            /** Feed the hub population and calculate the surplus food. */
            output.surplusFood = popHub.feedHub(gameData, output.foodProduction)

            turnData.put(popHub, output)
        }

        return gameData
    }

    @Test
    void testTurnAlgorithm() {

        // Missing: Recalculate Demand to PopHubs. Deal with PopUnit obedience.

        /** 1. DEAL WITH POP UNIT MULTIPLICATION. */
        gameData = popUnitsMultiply(gameData)

        /** 2. DEAL WITH POP UNIT PRODUCTION */
        gameData = popUnitsProduce(gameData)

        /** 3. DEAL WITH POP HUB PRODUCTION */
        gameData = popHubsRefine(gameData)

        /** 4. DEAL WITH GAME ACTORS */
        gameData.gameActors.each { player ->

        }




    }
}