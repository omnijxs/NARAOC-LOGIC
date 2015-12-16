package test

import game.GameData
import resources.common.Buildings
import resources.common.Product
import resources.common.Race
import resources.common.Tile
import resources.gameActor.GameActor
import resources.gameActor.Player
import org.junit.Before
import org.junit.Test
import resources.popHub.City
import resources.popHub.PopHub
import resources.popHub.PopHubOutput
import resources.popUnit.ArmyUnit
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import resources.popUnit.State
import resources.popUnit.Worker
import traits.FeedsArmy

/**
 * Created by Juri on 2.11.2015.
 */
class FeedsArmyTest {

    private class ArmyFeeder extends GameActor implements FeedsArmy {}

    protected GameData gameData
    protected ArmyFeeder player
    protected ArmyFeeder opponent
    protected PopHub city
    protected Tile cityTile

    @Before
    void setUp(){
        gameData = new GameData()
        player = new ArmyFeeder()

        cityTile = new Tile(x: 1, y: 1)

        city = new City(tile: cityTile, buildings: new Buildings(), owner: player)

        gameData.popHubs = [city]
    }

    @Test
    void testFeedArmy() {

        PopUnit a = new ArmyUnit(owner: player, starving: true)

        gameData.popUnits = [a]

        assert player.feedArmy(gameData, 1) == 0
        assert !a.starving
    }

    @Test
    void testDoNotFeedArmy() {

        opponent = new ArmyFeeder()

        PopUnit a = new ArmyUnit(owner: opponent, starving: true)

        gameData.popUnits = [a]

        assert player.feedArmy(gameData, 1) == 1
        assert a.starving
    }

    @Test
    void testNotEnoughFood() {

        PopUnit a = new ArmyUnit(owner: player, starving: true)
        PopUnit b = new ArmyUnit(owner: player, starving: true)
        PopUnit c = new ArmyUnit(owner: player, starving: true)

        gameData.popUnits = [a, b, c]

        assert player.feedArmy(gameData, 1) == 0
        assert !a.starving
        assert b.starving
        assert c.starving
    }

    @Test
    void testDoNotFeedNonStarving() {

        PopUnit a = new ArmyUnit(owner: player, starving: false)

        gameData.popUnits = [a]

        assert player.feedArmy(gameData, 1) == 1
        assert !a.starving
    }

    @Test
    void testDoNotFeedNonArmy() {

        PopUnit a = new Worker(starving: true)

        gameData.popUnits = [a]

        assert player.feedArmy(gameData, 1) == 1
        assert a.starving
    }

    @Test
    void testGetSurplusFoodOneCity() {

        PopHubOutput output = new PopHubOutput(surplusFood: 2)

        city.setTurnData(output)
        def surplusFood = player.getSurplusFood(gameData)

        assert surplusFood == 2
    }

    @Test
    void testGetSurplusFoodTwoCities() {

        PopHub c_1 = new City(owner: player)
        PopHubOutput output_1 = new PopHubOutput(surplusFood: 2)
        c_1.setTurnData(output_1)

        PopHub c_2 = new City(owner: player)
        PopHubOutput output_2 = new PopHubOutput(surplusFood: 3)
        c_2.setTurnData(output_2)

        gameData.popHubs = [c_1, c_2]

        def surplusFood = player.getSurplusFood(gameData)

        assert surplusFood == 5
    }

    @Test
    void testGetSurplusSkipUnownedPopHub() {

        PopHub c_1 = new City(owner: player)
        PopHubOutput output_1 = new PopHubOutput(surplusFood: 2)
        c_1.setTurnData(output_1)

        PopHub c_2 = new City(owner: null)
        PopHubOutput output_2 = new PopHubOutput(surplusFood: 3)
        c_2.setTurnData(output_2)

        gameData.popHubs = [c_1, c_2]

        def surplusFood = player.getSurplusFood(gameData)

        assert surplusFood == 2
    }


}
