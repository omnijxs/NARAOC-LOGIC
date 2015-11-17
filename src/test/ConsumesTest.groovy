package test

import org.junit.Test
import resources.popUnit.PopUnit
import traits.Consumes

/**
 * Created by Juri on 17.11.2015.
 */
class ConsumesTest implements Consumes {

    @Test
    void testConsumesNoSurplus() {

        assert starving

        def surplusFood = consume(1)

        assert !starving
        assert surplusFood == 0
    }

    @Test
    void testConsumesSurplus() {

        assert starving

        def surplusFood = consume(2)

        assert !starving
        assert surplusFood == 1
    }

    /**
     * This may not be the desired behaviour but this matches the implementation.
     * Consume-method requires that zero and negative checks are done on a higher level
     */
    @Test
    void testConsumesNoNegativeCheck() {

        assert starving

        def surplusFood = consume(0)

        assert !starving
        assert surplusFood == -1
    }
}
