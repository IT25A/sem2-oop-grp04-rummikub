package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable

@Serializable

data class Rack(
    private val playerId: PlayerId,
    private val tiles: List<Tile>,
    private var melded: Boolean = false

) {
    // Query
    fun owner() = playerId
    fun tiles() = tiles
    fun melded() = melded
    
    // Commands
    fun removeTiles(tilesToRemove: List<Tile>): Rack {
        val playerTiles = tiles.toMutableList().apply { tilesToRemove.forEach { remove(it) } }.toList()
            return copy(
                playerId= owner(),
                tiles = playerTiles,
                melded = true)

    }

    fun addTiles(tilesToAdd: List<Tile>): Rack {
        return copy(
            playerId = owner(),
            tiles = (tilesToAdd + tiles)
        )
    }
}
