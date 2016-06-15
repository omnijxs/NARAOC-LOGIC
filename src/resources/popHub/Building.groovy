package resources.popHub

import resources.common.Product
import resources.common.Race

/**
 * Created by jxs on 10.12.2015.
 */
class Building {

    Race race
    Product product /** Army, Magic, Food, Work, Trade */
    Integer priority /** 1, 2, 3, 4, 5 not needed anymore? */
    Integer upkeep
    Integer build
    // List<Perk> buildingPerks

    public getValue(){
        return build    // TODO take care of upkeep
    }

}