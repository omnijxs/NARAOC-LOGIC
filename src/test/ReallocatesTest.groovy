package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.gameActor.GameActor
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import traits.HasObedience
import traits.Reallocates

/**
 * Created by jxs on 6.12.2015.
 */
class ReallocatesTest {

    // NOTE Add tests to resolveState()
    // NOTE These tests also indirectly test PopUnit.canReallocate() and PopUnit.isObedient()

    private class MockUnit extends PopUnit implements Reallocates { }
    
    protected GameData gameData
    protected GameActor gameActor
    protected MockUnit original
    protected def gameInput

    @Before
    void setUp() {
        gameData = new GameData()
        gameActor = new GameActor()
        gameInput = [popUnitClass: '', popUnitType: '', reallocator: '', gameActor: null]
        original = new MockUnit(obedience: new HasObedience())
        gameData.popUnits = [original]
    }

    @Test
    void testCanReallocateFail() {

        original.obedience.value = 0                            /** The pop unit is not obedient */

        gameInput.popUnitClass = "resources.popUnit.Farmer"     /** User tries to reallocate it to a Farmer */ 

        gameData = original.reallocate(gameData, gameInput)

        assert gameData.popUnits.size() == 1                    /** GameData.popUnits should be unchanged */
        assert gameData.popUnits.first() == original
        assert gameData.popUnits.first().class == MockUnit
    }
    
    @Test
    void testCanReallocatePass() {

        original.obedience.value = 1                            /** The pop unit is obedient enough */

        gameInput.popUnitClass = "resources.popUnit.Farmer"     /** User tries to reallocate it to a Farmer */

        gameData = original.reallocate(gameData, gameInput)

        assert gameData.popUnits.size() == 1
        assert gameData.popUnits.first() != original            /** The original should be gone */
        assert gameData.popUnits.first().class == Farmer        /** And the new one should be in it's place */
    }
    
    @Test
    void testCreateNewInstance() {

        gameInput.popUnitClass = "resources.popUnit.Farmer"         /** Not optimal to test de facto classes but unable to load mockClass inside testClass */

        def reallocated = original.createNewInstance(gameInput)

        assert reallocated.class == Farmer
    }

    @Test
    void testManipulateGameData() {

        gameInput.popUnitClass = "resources.popUnit.Merchant"

        def reallocated = original.createNewInstance(gameInput)

        gameData.popUnits.add(reallocated)

        GameData mutatedGameData = original.manipulateGameData(gameData)

        assert mutatedGameData.popUnits.size() == 1
        assert mutatedGameData.popUnits.contains(reallocated)
        assert !mutatedGameData.popUnits.contains(original)

    }

}
