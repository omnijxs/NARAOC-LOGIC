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

        gameData.turnData = []
        gameData.popHubs = [city]

        farmer = new Farmer(state: new State(tile: cityTile, race: new Race()))
        worker = new Worker(state: new State(tile: cityTile, race: new Race()))
        merchant = new Merchant(state: new State(tile: cityTile, race: new Race()))

        gameData.popUnits = [farmer, worker, merchant]

    }

    // TODO TESTS
    protected GameData popUnitsMultiply(GameData gd){

        List<PopUnit> newPopUnits = []

        gd.popUnits.each { popUnit ->
            def a = popUnit.multiply()
            if(a)
                newPopUnits.add(popUnit.multiply())
        }

        gd.popUnits.addAll(newPopUnits)

        return gd
    }

    // TODO TESTS
    protected GameData popUnitsProduce(GameData gd){
        gd.popUnits.each { popUnit ->
            popUnit.resolvepreferredHub(gd)

            /** TileFeeding popUnits feed their tiles and set the surplus as their this turns production.
             Also set the production “flags” up to their popUnits */
            popUnit.produce(gd)
        }

        return gd
    }

    // TODO TESTS
    protected GameData popHubsRefine(GameData gd){

        Map<PopHub, PopHubOutput> data = [:]

        gd.popHubs.each { popHub ->

            /** Calculate bonuses, deal with buildings etc. */
            PopHubOutput output = popHub.refine(gd)

            /** Feed the hub population and calculate the surplus food. */
            output.surplusFood = popHub.feedHub(gd, output.foodProduction)

            data.put(popHub, output)
        }

        gd.turnData.push(data)

        return gd
    }

    // TODO TESTS
    protected GameData gameActorsSetup(GameData gd){
        gd.gameActors.each { player ->

            /** Calculate total surplus food */
            // Integer foodForArmies

            /** Feed your roaming armies...*/
            // Integer surplusFood = player.feedArmy(gameData, foodForArmies)

            /** Deal with taxation */
        }

        return gd
    }

    @Test
    void testTurnAlgorithm() {

        // Missing: Recalculate Demand to PopHubs. Deal with PopUnit obedience. Taxation. Set all PopUnits to starving.

        /** 1. DEAL WITH POP UNIT MULTIPLICATION. */
        gameData = popUnitsMultiply(gameData)

        /** 2. DEAL WITH POP UNIT PRODUCTION */
        gameData = popUnitsProduce(gameData)

        /** 3. DEAL WITH POP HUB PRODUCTION */
        gameData = popHubsRefine(gameData)

        /** 4. DEAL WITH GAME ACTORS */
        gameData = gameActorsSetup(gameData)

        /** 5. TURN-BASED ACTIONS... */

    }
}