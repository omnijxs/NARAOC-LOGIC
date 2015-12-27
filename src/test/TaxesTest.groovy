package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.gameActor.GameActor
import resources.gameActor.GameActorOutput
import resources.gameActor.Player
import resources.popHub.City
import resources.popHub.PopHub
import resources.popHub.PopHubOutput
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.popUnit.Worker
import resources.popUnit.obedience.Obedience

/**
 * Created by jxs on 16.12.2015.
 */
class TaxesTest {

    protected GameData gameData
    protected GameActor player
    protected PopUnit farmer
    protected PopUnit worker
    protected PopUnit merchant
    PopHubOutput output


    @Before
    void setUp() {
        gameData = new GameData()
        player = new Player()

        farmer = new Farmer(obedience: new Obedience())
        worker = new Worker(obedience: new Obedience())
        merchant = new Merchant(obedience: new Obedience())
        output = new PopHubOutput()
        output.food.put(farmer, 1)
        output.work.put(farmer, 1)
        output.trade.put(farmer, 1)
    }

    @Test
    void testGetTotalOutputBasicCase() {

        PopHub city = new City(owner: player)
        city.setTurnData(output)

        gameData.popHubs = [city]

        GameActorOutput total = player.getTotalOutput(gameData)

        assert total.foodTotal == 1
        assert total.workTotal == 1
        assert total.tradeTotal == 1
    }

    @Test
    void testGetTotalOutputSkipDisobedient() {

        PopHub city = new City(owner: player)
        city.setTurnData(output)

        gameData.popHubs = [city]

        farmer.obedience.value = 0
        worker.obedience.value = 0
        merchant.obedience.value = 0

        GameActorOutput total = player.getTotalOutput(gameData)

        assert total.foodTotal == 0
        assert total.workTotal == 0
        assert total.tradeTotal == 0
    }

    @Test
    void testGetTotalOutputTwoCities() {

        PopHub c_1 = new City(owner: player)
        PopHubOutput output_1 = new PopHubOutput(foodProduction: 2, workProduction: 1, tradeProduction: 1)
        c_1.setTurnData(output_1)

        PopHub c_2 = new City(owner: player)
        PopHubOutput output_2 = new PopHubOutput(foodProduction: 1, workProduction: 2, tradeProduction: 2)
        c_2.setTurnData(output_2)

        gameData.popHubs = [c_1, c_2]

        GameActorOutput total = player.getTotalOutput(gameData)

        assert total.foodTotal == 3
        assert total.workTotal == 3
        assert total.tradeTotal == 3
    }

    @Test
    void testGetTotalOutputSkipUnownedPopHub() {

        PopHub c_1 = new City(owner: player)
        PopHubOutput output_1 = new PopHubOutput(foodProduction: 2, workProduction: 1, tradeProduction: 1)
        c_1.setTurnData(output_1)

        PopHub c_2 = new City(owner: new Player())
        PopHubOutput output_2 = new PopHubOutput(foodProduction: 1, workProduction: 2, tradeProduction: 2)
        c_2.setTurnData(output_2)

        gameData.popHubs = [c_1, c_2]

        GameActorOutput total = player.getTotalOutput(gameData)

        assert total.foodTotal == 2
        assert total.workTotal == 1
        assert total.tradeTotal == 1
    }

    @Test
    void testTaxBasicCase() {

        player.foodTaxRate = player.workTaxRate = player.tradeTaxRate = 50

        GameActorOutput output = new GameActorOutput(foodTotal: 10, workTotal: 10, tradeTotal: 10)

        player.setTurnData(output)

        Integer total = player.tax()

        assert total == 15
    }

    @Test
    void testTaxRounding() {

        player.foodTaxRate = player.workTaxRate = player.tradeTaxRate = 50

        GameActorOutput output = new GameActorOutput(foodTotal: 10, workTotal: 10, tradeTotal: 5)

        player.setTurnData(output)

        Integer total = player.tax()

        /** Currently we round down. */
        assert total == 12
    }

}
