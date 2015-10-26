package test

import game.Game
import org.junit.Before
import org.junit.Test
import resources.popUnit.ArmyUnit
import resources.popUnit.Farmer
import resources.common.GameMap
import resources.popUnit.PopUnit
import resources.common.Tile

/**
 * Created by Juri on 22.10.2015.
 */
class FeedTileTest {

    protected Game game
    protected GameMap gameMap
    protected Tile emptyTile
    protected Tile filledTile
    protected PopUnit farmer
    protected PopUnit armyUnit

    @Before
    void setUp(){
        game = new Game()                       // TODO RETHINK THIS SHIT
        gameMap = new GameMap()
        game.map = gameMap
        gameMap.game = game

        emptyTile = new Tile(map: gameMap)
        filledTile = new Tile(map: gameMap)
        farmer = new Farmer(starving: true)     // TODO GET RID OF THIS BOILERPLATE!
        armyUnit = new ArmyUnit(starving: true)

        game.map.tiles = [emptyTile, filledTile]
        game.popUnits = [farmer, armyUnit]

    }

    @Test
    void testFarmerFeedsArmyUnit() {

        farmer.tile = filledTile
        armyUnit.tile = filledTile

        assert farmer.produce() == 0
        assert !farmer.starving
        assert !armyUnit.starving
    }

    @Test
    void testFarmerFeedsItself() {

        farmer.tile = filledTile

        assert farmer.produce() == 1
        assert !farmer.starving
        assert armyUnit.starving
    }

    @Test
    void testFarmerDoesNotFeedNonArmyUnits() {

        PopUnit a = new Farmer(tile: filledTile, starving: true)
        PopUnit b = new Farmer(tile: filledTile, starving: true)

        game.popUnits.addAll([a, b])
        farmer.tile = filledTile

        assert farmer.produce() == 1
        assert !farmer.starving
        assert a.starving
        assert b.starving
    }

    @Test
    void testFarmerDoesNotReturnNegativeValue() {

        PopUnit a = new ArmyUnit(tile: filledTile, starving: true)
        PopUnit b = new ArmyUnit(tile: filledTile, starving: true)

        game.popUnits.addAll([a, b])

        farmer.tile = filledTile

        assert farmer.produce() == 0

    }

    @Test
    void testFarmerFeedsArmyUnitsBeforeSelf() {

        PopUnit a = new ArmyUnit(tile: filledTile, starving: true)
        PopUnit b = new ArmyUnit(tile: filledTile, starving: true)

        game.popUnits.addAll([a, b])

        farmer.tile = filledTile

        assert farmer.produce() == 0
        assert farmer.starving
        assert !a.starving
        assert !b.starving
    }

}
