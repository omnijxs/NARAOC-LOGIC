package test

import game.GameData
import resources.gameActor.Player
import org.junit.Before
import org.junit.Test
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit
import resources.popUnit.Worker
import traits.FeedsArmy

/**
 * Created by Juri on 2.11.2015.
 */
class FeedsArmyTest {

    private class ArmyFeeder extends Player implements FeedsArmy {}

    protected GameData gameData
    protected ArmyFeeder player
    protected ArmyFeeder opponent

    @Before
    void setUp(){
        gameData = new GameData()
        player = new ArmyFeeder()
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

}
