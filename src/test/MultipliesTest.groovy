package test

import org.junit.Before
import org.junit.Test
import resources.popUnit.PopUnit
import resources.popUnit.State
import traits.Multiplies

/**
 * Created by jxs on 2.12.2015.
 */
class MultipliesTest {

    private class MockUnit extends PopUnit implements Multiplies {

        public MockUnit(){
            this.state = new State()
        }
    }

    @Before
    void setUp(){

    }

    @Test
    void testCopy() {

        MockUnit a = new MockUnit()

        assert a.copy().class == MockUnit
        assert a.copy() != a
    }

    @Test
    void testMultiplyAlways() {

        MockUnit a = new MockUnit()

        a.multiplicationRate = 100

        assert a.multiply().class == MockUnit

    }

    @Test
    void testMultiplyNever() {

        MockUnit a = new MockUnit()

        a.multiplicationRate = 0

        assert !a.multiply()

    }
}
