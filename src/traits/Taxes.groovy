package traits

import resources.gameActor.GameActorOutput

/**
 * Created by jxs on 16.12.2015.
 */
trait Taxes {

    private List<GameActorOutput> turnData = []

    Integer foodTaxRate = 0
    Integer workTaxRate = 0
    Integer tradeTaxRate = 0

    def tax(){

    }

}