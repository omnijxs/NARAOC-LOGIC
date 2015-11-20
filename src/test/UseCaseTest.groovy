package test

import game.GameData
import org.junit.Before
import org.junit.Test
import resources.popHub.City
import resources.common.GameMap
import resources.common.Tile
import resources.popHub.HubProduction
import resources.popHub.PopHub
import resources.popUnit.Farmer
import resources.popUnit.State

/**
 * Created by Juri on 16.11.2015.
 */
class UseCaseTest {

    protected Map<PopHub, HubProduction> turnProduction     // TODO RENAME
    protected GameData gameData
    protected GameMap gameMap
    protected PopHub city
    protected Tile cityTile

    @Before
    void setUp() {
        gameData = new GameData()
        gameMap = new GameMap()
        gameData.gameMap = gameMap

        cityTile = new Tile(x: 1, y: 1)

        city = new City(tile: cityTile)

        gameMap.hubs = [city]

        gameData.popUnits = [new Farmer(state: new State(tile: cityTile))]

        turnProduction = [:]

    }

    @Test
    void testTurnAlgorithm() {

        // TODO Recalculate demand to hubs
        // TODO Population of the city an own method. Or city searches themself?
        // TODO All could be threads

        /** Deal with popUnits */
        gameData.popUnits.each { popUnit ->

            popUnit.resolvepreferredHub(gameData)

            /** TileFeeding popUnits feed their tiles and set the surplus as their this turns production.
            Also set the production “flags” up to their popUnits */
            popUnit.produce()

        }

        /** Deal with hubs */
        gameData.hubs.each { popHub ->

            /** Population of the city */
            // def popUnits = gameData.popUnits.findAll { it.preferredHub == popHub || it.tile == popHub.tile }

            /** Calculate bonuses, deal with buildings etc. */
            HubProduction hubProduction = popHub.refine(gameData)

            turnProduction.put(popHub, hubProduction)
       }

        /** Deal with players */
        gameData.players.each {player ->

        }




    }
}