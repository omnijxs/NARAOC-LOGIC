package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.Buildings
import resources.common.Race
import resources.common.Tile
import resources.gameActor.GameActor
import resources.gameActor.Player
import resources.popHub.City
import resources.popHub.PopHub
import resources.popHub.PopHubOutput
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.popUnit.State
import resources.popUnit.Worker

/**
 * Created by jxs on 10.12.2015.
 */
class RefinesTest {

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

        farmer = new Farmer(state: new State(tile: new Tile(), race: new Race()), preferredHub: city)
        worker = new Worker(state: new State(tile: cityTile, race: new Race()), preferredHub: city)
        merchant = new Merchant(state: new State(tile: cityTile, race: new Race()), preferredHub: city)

        gameData.popUnits = [farmer, worker, merchant]

    }

    @Test
    void testNoOutputWithoutProduce() {

        PopHubOutput output = city.refine(gameData)

        assert output.foodProduction == 0
        assert output.workProduction == 0
        assert output.tradeProduction == 0
    }

    @Test
    void testBasicOutputNoBuildings() {

        gameData.popUnits.each { it.produce(gameData) }

        PopHubOutput output = city.refine(gameData)

        assert output.foodProduction == 1
        assert output.workProduction == 1
        assert output.tradeProduction == 1
    }

    @Test
    void testTurnDataSequence() {

        PopHubOutput output_1 = city.refine(gameData)
        city.setTurnData(output_1)

        assert city.getTurnData() == output_1

        PopHubOutput output_2 = city.refine(gameData)
        city.setTurnData(output_2)

        assert city.getTurnData() == output_2

    }
}
