package traits

import resources.common.Product
import resources.common.Race
import resources.popHub.Building
import resources.popHub.BuildingProduction
import resources.popHub.PopHubOutput
import resources.popUnit.PopUnit

/**
 * Created by jxs on 15.6.2016.
 */
trait HasBuildings {

    List<BuildingProduction> production = []
    List<Building> buildingConfiguration = [] // TODO FROM CONFIG

    List<Building> builds(PopHubOutput output){

        List<Building> buildings = []

        output.getAllPopUnits().each { popUnit, value ->
            build(popUnit, value)
        }

        return buildings
    }

    BuildingProduction build(PopUnit popUnit, Integer value){

        BuildingProduction prod = production.find { it.race == popUnit.race && it.product == popUnit.product }

        if(!prod){
            prod = new BuildingProduction(race: popUnit.race, product: popUnit.product, value: 0)
            production.add(prod)        // TODO do this elsewhere
        }

        prod.value += value

        return prod
    }

    List<Building> buildingsForPath(Race race, Product product, Integer value){
        // Integer value = production.find { it.race == race && it.product == product }.value

        List<Building> buildings = buildingConfiguration.findAll { it.race == race && it.product == product && it.value <= value }

        return buildings
    }

    List<Building> buildingsForPaths(){

        List<Building> buildings = []

        production.each { it ->
            buildings.addAll(buildingsForPath(it.race, it.product, it.value))
        }

        return buildings
    }
}