package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import resources.popUnit.State
import traits.Reallocates

/**
 * Created by jxs on 6.12.2015.
 */
class ReallocatesTest {

    protected GameData gameData
    protected def gameInput

    private class MockUnit extends PopUnit implements Reallocates {
        public MockUnit(){
            state = new State()
        }
    }

    @Before
    void setUp() {
        gameData = new GameData()
        gameInput = [popUnitClass: '', popUnitType: '', reallocator: '']
    }

    @Test
    void testReallocate() {

        MockUnit a = new MockUnit()

        gameInput.popUnitClass = "resources.popUnit.Farmer"         /** Not optimal to test de facto classes but unable to load mockClass inside testClass */

        def reallocated = a.reallocate(gameInput)

        assert reallocated.class == Farmer

    }
}
