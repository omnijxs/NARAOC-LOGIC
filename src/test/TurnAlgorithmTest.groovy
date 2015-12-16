package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.Buildings
import resources.common.Race
import resources.gameActor.GameActor
import resources.gameActor.Player
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
class TurnAlgorithmTest {

    protected GameData gameData
    protected PopHub city
    protected GameActor player
    protected PopUnit farmer
    protected PopUnit worker
    protected PopUnit merchant
    protected Tile cityTile

    @Before
    void setUp() {
        gameData = new GameData()

        player = new Player()

        cityTile = new Tile(x: 1, y: 1)

        city = new City(tile: cityTile, buildings: new Buildings(), owner: player)

        gameData.popHubs = [city]
        gameData.gameActors = [player]

        farmer = new Farmer(state: new State(tile: cityTile, race: new Race()))
        worker = new Worker(state: new State(tile: cityTile, race: new Race()))
        merchant = new Merchant(state: new State(tile: cityTile, race: new Race()))

        gameData.popUnits = [farmer, worker, merchant]

    }

    @Test
    void testTurnAlgorithm() {

        /** 1. DEAL WITH POP UNIT MULTIPLICATION. */
        gameData = popUnitsMultiply(gameData)           /** gameData.popUnits = ...*/

        /** 2. DEAL WITH POP UNIT PRODUCTION */
        gameData = popUnitsProduce(gameData)            /** gameData.popUnits = ...*/

        /** 3. DEAL WITH POP HUB PRODUCTION */
        gameData = popHubsRefine(gameData)

        /** 4. DEAL WITH GAME ACTORS */
        gameData = gameActorsSetup(gameData)

        /** 5. TURN-BASED ACTIONS... */
        gameData = gameActorInput(gameData)

        /** 6. PREPARE GAME DATA FOR THE NEXT TURN */
        gameData = postProcess(gameData)

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

            /** All pop units calculate to which city they will produce to. */
            popUnit.resolvepreferredHub(gd)

            /** Set production flags up in all popUnits.
             * TileFeeding popUnits feed their tiles and set the surplus as their this turns production. */
            popUnit.produce(gd)
        }

        return gd
    }

    // TODO TESTS
    protected GameData popHubsRefine(GameData gd){

        gd.popHubs.each { popHub ->

            /** Calculate bonuses, deal with buildings etc. */
            PopHubOutput output = popHub.refine(gd)

            /** Feed the hub population and calculate the surplus food. */
            output.surplusFood = popHub.feedHub(gd, output.foodProduction)

            /** */
            popHub.setTurnData(output)
        }

        return gd
    }

    // TODO TESTS
    protected GameData gameActorsSetup(GameData gd){
        gd.gameActors.each { player ->
            /** Lets feed your roaming armies...*/
            Integer surplusFood = feedArmies(gd, player)

            /** Deal with taxation in a separate method. Store the info the player. */
        }

        return gd
    }

    // TODO TESTS
    protected Integer feedArmies(GameData gd, GameActor ga){

        Integer foodForArmies = 0

        /** Find cities which produce for me... */
        def obedientHubs = gd.popHubs.findAll { it.owner == ga }

        /** Calculate total surplus food. */
        obedientHubs.each { popHub ->

            def turnData = popHub.getTurnData()

            foodForArmies += turnData.surplusFood
        }

        return ga.feedArmy(gd, foodForArmies)
    }

    protected GameData gameActorInput(GameData gd){
        gd.gameActors.each { player ->
            gd = yieldControl(gd, player)
        }

        return gd
    }

    protected GameData yieldControl(GameData gd, GameActor a){
        return gd
            /** The actual player/AI input */
    }

    protected GameData postProcess(GameData gd){

        /** Calculate demand for pop hubs */
        gd.popHubs.each { popHub ->
            popHub.setDemand(gd)
        }

        /** Deal with pop unit obedience. Note that this MUST be before we set them to starving. */
        gd.popUnits.each { popUnit ->
            // popUnit.dealWithObedience()
        }

        /** Set all PopUnits to starving for next turn. Could be done in pre-process. */
        gd.popUnits.each { popUnit ->
            popUnit.starving = true
        }

        return gd

    }

}