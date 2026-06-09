package hwr.oop.examples.template.core

import kotlinx.serialization.Serializable

@Serializable
data class Rack(
    private val owner: PlayerId,
    private val tiles: List<Tile>,
    private val opened: Boolean = false
) {
    //Commands
    fun removeTiles(tilesToRemove: List<Tile>): Rack {
        val updatedTiles = tiles.toMutableList()
            .apply { tilesToRemove.forEach { remove(it) } }

        return copy(owner= owner, tiles = updatedTiles, opened = true)
    }

    fun addTiles(tilesToAdd: List<Tile>): Rack = copy(owner = owner, tiles = tiles + tilesToAdd)


    //Query
    fun owner(): PlayerId = owner
    fun tiles(): List<Tile> = tiles.toList()
    fun hasTile(tile: Tile): Boolean = tile in tiles
    fun isOpen(): Boolean = opened
}