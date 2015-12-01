package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popUnit.ArmyUnit
import resources.popUnit.PopUnit
import resources.common.Tile
import resources.popUnit.State
import traits.Consumes
import traits.FeedsTile

/**
 * Created by Juri on 22.10.2015.
 */
class FeedsTileTest {

    private class TileFeeder extends PopUnit implements FeedsTile, Consumes {

        public TileFeeder(){
            state = new State()
        }
    }
    
    protected GameData gameData
    protected Tile emptyTile
    protected Tile filledTile
    protected PopUnit army
    protected PopUnit feeder
    protected PopUnit extra

    @Before
    void setUp(){
        gameData = new GameData()                       // TODO RETHINK THIS SHIT

        emptyTile = new Tile()
        filledTile = new Tile()
        army = new ArmyUnit()
        feeder = new TileFeeder(priority: 2)            // TODO A fancier way of doing te priority sort

        gameData.mapTiles = [emptyTile, filledTile]

        gameData.popUnits = [army, feeder]

    }

    @Test
    void testFeedArmyUnit() {

        army.tile = filledTile
        feeder.tile = filledTile

        assert feeder.feedTile(gameData, 2) == 0            // TODO use productAmount
        assert !feeder.starving
        assert !army.starving
    }

    @Test
    void testFeedSelf() {

        army.tile = emptyTile
        feeder.tile = filledTile

        assert feeder.feedTile(gameData, 2) == 1
        assert !feeder.starving
        assert army.starving
    }

    @Test
    void testDontFeedNonArmyUnits() {

        extra = new TileFeeder()
        gameData.popUnits.add(extra)
        
        feeder.tile = filledTile
        extra.tile = filledTile

        assert feeder.feedTile(gameData, 2) == 1
        assert !feeder.starving
        assert extra.starving
    }

    @Test
    void testFeedArmyUnitsBeforeSelf() {

        extra = new ArmyUnit()

        gameData.popUnits.add(extra)

        army.tile = filledTile
        extra.tile = filledTile
        feeder.tile = filledTile

        assert feeder.feedTile(gameData, 2) == 0
        assert feeder.starving
        assert !army.starving
        assert !extra.starving
    }

}
