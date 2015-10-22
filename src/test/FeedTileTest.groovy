package test

import game.Game
import org.junit.Before
import org.junit.Test
import resources.ArmyUnit
import resources.Farmer
import resources.GameMap
import resources.PopUnit
import resources.Tile

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
        game = new Game()
        gameMap = new GameMap()
        game.map = gameMap
        gameMap.game = game

        emptyTile = new Tile(map: gameMap)
        filledTile = new Tile(map: gameMap)
        farmer = new Farmer()
        armyUnit = new ArmyUnit()

        game.map.tiles = [emptyTile, filledTile]
        game.popUnits = [farmer, armyUnit]

    }

    @Test
    void testFarmerFeedsArmyUnit() {

        farmer.tile = filledTile
        armyUnit.tile = filledTile

        assert farmer.produce() == 0
    }

    @Test
    void testFarmerFeedsItself() {

        farmer.tile = filledTile
        armyUnit.tile = emptyTile

        assert farmer.produce() == 1
    }

    // private void getGame()
}
