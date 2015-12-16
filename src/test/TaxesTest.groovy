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

/**
 * Created by jxs on 16.12.2015.
 */
class TaxesTest {

    protected GameData gameData
    protected GameActor player

    @Before
    void setUp() {
        gameData = new GameData()
        player = new Player()
    }

    @Test
    void testGetTotalOutputBasicCase() {

        PopHub c_1 = new City(owner: player)
        PopHubOutput output_1 = new PopHubOutput(foodProduction: 2, workProduction: 1, tradeProduction: 1)
        c_1.setTurnData(output_1)

        gameData.popHubs = [c_1]

        GameActorOutput total = player.getTotalOutput(gameData)

        assert total.foodTotal == 2
        assert total.workTotal == 1
        assert total.tradeTotal == 1
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

}
