package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.common.Race
import resources.popHub.City
import resources.common.Tile
import resources.popHub.PopHubOutput
import resources.popHub.PopHub
import resources.popUnit.Farmer
import resources.popUnit.PopUnit
import resources.popUnit.State

/**
 * Created by Juri on 16.11.2015.
 */
class UseCaseTest {

    protected Map<PopHub, PopHubOut> turnData     // TODO RENAME
    protected GameData gameData
    protected PopHub city
    protected Tile cityTile

    @Before
    void setUp() {
        gameData = new GameData()

        cityTile = new Tile(x: 1, y: 1)

        city = new City(tile: cityTile)

        gameData.popHubs = [city]

        gameData.popUnits = [new Farmer(state: new State(tile: cityTile, race: new Race()))]

        turnData = [:]

    }

    @Test
    void testTurnAlgorithm() {

        // TODO Recalculate demand to hubs

        /** Deal with popUnit-multiplication */
        List<PopUnit> newPopUnits = []

        gameData.popUnits.each { popUnit ->
            def a = popUnit.multiply()
            if(a)
                newPopUnits.add(popUnit.multiply())
        }

        gameData.popUnits.addAll(newPopUnits)


        /** Deal with popUnits */
        gameData.popUnits.each { popUnit ->

            popUnit.resolvepreferredHub(gameData)

            /** TileFeeding popUnits feed their tiles and set the surplus as their this turns production.
            Also set the production “flags” up to their popUnits */
            popUnit.produce(gameData)

        }

        /** Deal with popHubs */
        gameData.popHubs.each { popHub ->

            /** Calculate bonuses, deal with buildings etc. */
            PopHubOutput popHubOutput = popHub.refine(gameData)

            turnData.put(popHub, popHubOutput)
       }

        /** Deal with gameActors */
        gameData.gameActors.each { player ->

        }




    }
}