package test

import org.junit.Before
import org.junit.Test
import resources.common.Race
import resources.popUnit.PopUnit
import resources.popUnit.obedience.Obedience
import traits.Consumes
import traits.Multiplies

/**
 * Created by jxs on 2.12.2015.
 */
class MultipliesTest {

    private class MockUnit extends PopUnit implements Multiplies, Consumes {

        public MockUnit(){
            this.race = new Race()
            this.obedience = new Obedience()
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
        a.starving = false

        a.multiplicationRate = 100

        assert a.multiply().class == MockUnit
    }

    @Test
    void testNeverMultiplyStarving() {

        MockUnit a = new MockUnit()
        a.starving = true

        a.multiplicationRate = 100

        assert !a.multiply()
    }

    @Test
    void testMultiplyNever() {

        MockUnit a = new MockUnit()
        a.starving = false

        a.multiplicationRate = 0

        assert !a.multiply()
    }
}
