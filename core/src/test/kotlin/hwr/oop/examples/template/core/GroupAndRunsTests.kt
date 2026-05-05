package hwr.oop.examples.template.core

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class GroupAndRunsTests {
    //given
    private val player1 = PlayerId("player1")
    private val tiles = listOf(
        Tile(TileColor.BLACK, TileNumber.ONE ),
        Tile(TileColor.BLACK, TileNumber.TWO ),
        Tile(TileColor.BLACK, TileNumber.THREE ),
        Tile(TileColor.BLACK, TileNumber.FOUR ),
        Tile(TileColor.RED, TileNumber.TWELVE ),
        Tile(TileColor.BLUE, TileNumber.TWELVE ),
        Tile(TileColor.YELLOW, TileNumber.TWELVE ),
        Tile(TileColor.BLACK, TileNumber.TWELVE ),
    )
    private val playerOneRack = Rack(player1, tiles, false)

    @Test
    fun `create GROUP from rack` () {
        //when
        //from playerRack.tiles find selected tile and add to TileSet

        //then

        //Test if tile can go into TileSet
        //if yes add to tile set
        //if no give feedback as to why not
            //no more than four for a group
            //not numerically in Run
            //etc
        //once added >> add value to player "first turn" total
        //continue
        //once full enumerate it as GROUP / RUN
        //add to Table as valid Set


        //if not 30 points then player must draw


    }

    @Test
    fun `create RUN from rack` () {

    }

    @Test
    fun `First turn has 30 points` () {

    }

    @Test
    fun `first turn does not have 30 points` () {

    }
}