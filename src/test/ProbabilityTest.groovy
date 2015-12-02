package test

import org.junit.Test
import traits.Probability

/**
 * Created by jxs on 2.12.2015.
 */
class ProbabilityTest implements Probability {

    @Test
    void testGetComparison() {

        assert !getComparison(29, 30)
        assert getComparison(30, 30)
        assert getComparison(31, 30)
    }

}

