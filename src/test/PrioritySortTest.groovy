package test

import org.junit.Test
import resources.popUnit.ArmyUnit
import resources.popUnit.Farmer
import resources.popUnit.Merchant
import resources.popUnit.PopUnit
import resources.popUnit.Worker
import traits.PopUnitSorter

/**
 * Created by Juri on 23.10.2015.
 */
class PrioritySortTest implements PopUnitSorter {

    @Test
    void testSortedByPriority() {

        PopUnit a = new ArmyUnit()
        PopUnit b = new Farmer()
        PopUnit c = new ArmyUnit()

        List<PopUnit> popUnits = [a, b, c]

        assert prioritySortPopUnits(popUnits) == [a, c, b]
    }

    @Test
    void testSortedByPriorityAndAge() {

        PopUnit a = new ArmyUnit(age: 5)
        PopUnit b = new Farmer(age: 15)
        PopUnit c = new ArmyUnit(age: 10)

        List<PopUnit> popUnits = [a, b, c]

        assert prioritySortPopUnits(popUnits) == [c, a, b]
    }

    @Test
    void testSortedByPriorityAndAgeMultipleTypes() {

        PopUnit a = new Farmer(age: 5)
        PopUnit b = new Farmer(age: 15)
        PopUnit c = new Farmer(age: 10)
        PopUnit d = new ArmyUnit(age: 20)
        PopUnit e = new ArmyUnit(age: 10)

        List<PopUnit> popUnits = [a, b, c, d, e]

        assert prioritySortPopUnits(popUnits) == [d, e, b, c, a]
    }

    @Test
    void testSortedByPriorityAndAgeAllTypes() {

        PopUnit a = new Farmer(age: 5)
        PopUnit b = new Merchant(age: 15)
        PopUnit c = new Worker(age: 10)
        PopUnit d = new Worker(age: 20)

        PopUnit e = new ArmyUnit(age: 20)
        PopUnit f = new ArmyUnit(age: 10)

        List<PopUnit> popUnits = [a, b, c, d, e, f]

        assert prioritySortPopUnits(popUnits) == [e, f, d, b, c, a]
    }

}
