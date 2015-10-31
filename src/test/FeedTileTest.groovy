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
// TODO rename FeedsTileTest
class FeedTileTest {

    /** Why not use farmer but this... */
    private class TileFeeder extends PopUnits implements FeedsTile {}
    
    protected Game game
    protected GameMap gameMap
    protected Tile emptyTile
    protected Tile filledTile
    protected PopUnit army
    protected PopUnit feeder
    protected PopUnit extra

    @Before
    void setUp(){
        game = new Game()                       // TODO RETHINK THIS SHIT
        gameMap = new GameMap()
        game.map = gameMap
        gameMap.game = game

        emptyTile = new Tile(map: gameMap)
        filledTile = new Tile(map: gameMap) 
        army = new ArmyUnit()
        feeder = new TileFeeder()

        game.map.tiles = [emptyTile, filledTile]
        
        game.popUnits = [a, p]

    }

    @Test
    void testFeedArmyUnit() {

        army.tile = filledTile
        feeder.tile = filledTile

        assert feeder.feedTile() == 0            // TODO use productAmount
        assert !feeder.starving
        assert !army.starving
    }

    @Test
    void testFeedSelf() {

        army.tile = emptyTile
        feeder.tile = filledTile

        assert feeder.feedTile() == 1
        assert !feeder.starving
        assert army.starving
    }

    @Test
    void testDontFeedNonArmyUnits() {

        extra = new TileFeeder()
        game.popUnits.add(extra)
        
        feeder.tile = filledTile
        extra.tile = filledTile

        assert feeder.feedTile() == 1
        assert !feeder.starving
        assert extra.starving
    }

    @Test
    void testFeedArmyUnitsBeforeSelf() {

        extra = new ArmyUnit()

        game.popUnits.add(extra)

        army.tile = filledTile
        extra.tile = filledTile
        feeder.tile = filledTile

        assert feeder.feedTile() == 0
        assert feeder.starving
        assert !army.starving
        assert !extra.starving
    }

}
