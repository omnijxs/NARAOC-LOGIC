package test

import org.junit.Before
import org.junit.Test
import resources.common.Race
import resources.popUnit.PopUnit
import resources.popUnit.State
import traits.Multiplies

/**
 * Created by jxs on 2.12.2015.
 */
class MultipliesTest {

    private class MockUnit extends PopUnit implements Multiplies {

        public MockUnit(){
            this.state = new State(race: new Race())
        }
    }

    @Before
    void setUp(){

    }

    @Test
    void testBreed() {

        MockUnit a = new MockUnit()

        assert a.breed().class == MockUnit
        assert a.breed() != a
    }

    @Test
    void testMultiplyAlways() {

        MockUnit a = new MockUnit()

        a.state.race.multiplicationRate = 100

        assert a.multiply().class == MockUnit

    }

    @Test
    void testMultiplyNever() {

        MockUnit a = new MockUnit()

        a.state.race.multiplicationRate = 0

        assert !a.multiply()

    }
}
