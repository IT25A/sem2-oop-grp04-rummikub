package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class RackTest {
    //given
    private val player = PlayerId("player1")
    private val rack = Rack(player, listOf(
        Tile(TileColor.BLUE, TileNumber.THIRTEEN),
        Tile(TileColor.RED, TileNumber.THIRTEEN),
        Tile(TileColor.YELLOW, TileNumber.THIRTEEN),
        Tile(TileColor.BLACK, TileNumber.THIRTEEN),
    ))
    private val tilesToRemove = listOf(
        Tile(TileColor.BLUE, TileNumber.THIRTEEN),
        Tile(TileColor.RED, TileNumber.THIRTEEN),
        Tile(TileColor.YELLOW, TileNumber.THIRTEEN),
        Tile(TileColor.BLACK, TileNumber.THIRTEEN),
    )

    private val tilesToAdd = listOf(
        Tile(TileColor.BLUE, TileNumber.ONE),
    )

    @Test
    fun `rack tiles to remove exist in hand`() {
        //when
        val boolList = tilesToRemove.map { tile -> rack.hasTile(tile) }
        //then
        assertTrue(!boolList.contains(false))
    }

    @Test
    fun `rack tiles to remove do not exist in hand`() {
        //when
        val boolList = tilesToAdd.map { tile -> rack.hasTile(tile) }
        //then
        assertTrue(!boolList.contains(true))
    }

    @Test
    fun `rack tiles removed successfully`() {
        //when
        val updatedRack = rack.removeTiles(tilesToRemove)
        //then
        assertTrue(updatedRack.tiles().isEmpty())
    }

    @Test
    fun `rack tiles added successfully`() {
        //when
        val updatedRack = rack.addTiles(tilesToAdd)
        //then
        assertThat(updatedRack.tiles()).containsExactlyInAnyOrderElementsOf(rack.tiles() + tilesToAdd)
    }
}