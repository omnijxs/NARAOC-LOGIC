package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popUnit.Farmer
import resources.popUnit.PopUnit

import traits.Reallocates

/**
 * Created by jxs on 6.12.2015.
 */
class ReallocatesTest {

    protected GameData gameData
    protected def gameInput

    private class MockUnit extends PopUnit implements Reallocates {
    }

    @Before
    void setUp() {
        gameData = new GameData()
        gameInput = [popUnitClass: '', popUnitType: '', reallocator: '']
    }

    @Test
    void testReallocate() {

        MockUnit original = new MockUnit()

        gameInput.popUnitClass = "resources.popUnit.Farmer"         /** Not optimal to test de facto classes but unable to load mockClass inside testClass */

        def reallocated = original.reallocate(gameInput)

        assert reallocated.class == Farmer

    }

    @Test
    void testManipulateGameData() {

        MockUnit original = new MockUnit()

        gameInput.popUnitClass = "resources.popUnit.Merchant"

        def reallocated = original.reallocate(gameInput)

        gameData.popUnits = [original, reallocated]

        GameData mutatedGameData = original.manipulateGameData(gameData)

        assert mutatedGameData.popUnits.size() == 1
        assert mutatedGameData.popUnits.contains(reallocated)
        assert !mutatedGameData.popUnits.contains(original)

    }
}
