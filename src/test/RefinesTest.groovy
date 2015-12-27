package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popHub.Buildings
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

        farmer = new Farmer(tile: new Tile(), race: new Race(), preferredHub: city)
        worker = new Worker(tile: cityTile, race: new Race(), preferredHub: city)
        merchant = new Merchant(tile: cityTile, race: new Race(), preferredHub: city)

        gameData.popUnits = [farmer, worker, merchant]

    }

    @Test
    void testBasicPopHubOutputCase() {

        gameData.popUnits.each { it.produce(gameData) }

        PopHubOutput output = city.refine(gameData)

        assert output.food.size() == 1
        assert output.food.get(farmer) == 1

        assert output.work.size() == 1
        assert output.work.get(worker) == 1

        assert output.trade.size() == 1
        assert output.trade.get(merchant) == 1
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
