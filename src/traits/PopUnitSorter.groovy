package traits

import resources.popUnit.PopUnit

/**
 * Created by Juri on 23.10.2015.
 */
trait PopUnitSorter {

    // TODO rename method names!!! they are horrible!!! 
    
    /** *
     * Sort a popUnit list first by priority (the lower the better) AND then by age (the higher the better)     *
     * Priority is defined by the PopUnit class (ArmyUnit, MagicUnit, Farmer etc)
     *
     * @param popUnits Unsorted list of popUnits
     * @return Sorted list of popUnits
     */
    List<PopUnit> prioritySortPopUnits(List<PopUnit> popUnits){
        return popUnits.sort {a, b -> a.priority <=> b.priority ?: -a.age <=> -b.age }
        /** First sort by priority in ascending order. If they are equal the spaceship evaluates to zero which
         *  Groovy thinks is null and then sort by age in descending order */
    }
    
    List<PopUnit> priorityProductionSortPopUnits(List<PopUnit> popUnits){
        return popUnits.sort {a, b -> a.priority <=> b.priority ?: a.productionValue <=> b.productionValue ?: -b.age <=> -b.age }
    }
    
    
}