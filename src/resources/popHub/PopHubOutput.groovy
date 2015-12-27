package resources.popHub

import resources.popUnit.PopUnit

/**
 * Created by Juri on 17.11.2015.
 */
class PopHubOutput {

    Map<PopUnit, Integer> food = [:]
    Map<PopUnit, Integer> work = [:]
    Map<PopUnit, Integer> trade = [:]

    Integer surplusFood = 0
    Boolean closed = false

    /** Or use a stack where this turns production is on top? */

}
