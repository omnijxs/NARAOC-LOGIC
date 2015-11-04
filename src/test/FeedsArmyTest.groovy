package test

import game.Game
import game.Player
import org.junit.Before
import org.junit.Test
import resources.city.City
import resources.common.GameMap
import resources.common.Tile
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit
import traits.FeedsArmy

/**
 * Created by Juri on 2.11.2015.
 */
class FeedsArmyTest {

    private class ArmyFeeder extends Player implements FeedsArmy {}

    protected Game game
    protected ArmyFeeder player
    protected ArmyFeeder opponent

    protected PopUnit armyOutsideCity
    protected PopUnit nonArmyInsideCity
    protected PopUnit nonArmyOutsideCity

    @Before
    void setUp(){}

    @Test
    void testFeedArmy() {

        player = new ArmyFeeder()

        PopUnit a = new ArmyUnit(owner: player, starving: true)


        assert !a.starving
        /* armyInsideCity.tile = cityTile
        nonArmyInsideCity.tile = cityTile

        assert city.feedCity(2) == 0
        assert !armyInsideCity.starving
        assert !nonArmyInsideCity.starving */
    }

}
