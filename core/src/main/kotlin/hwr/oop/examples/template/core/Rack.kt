package hwr.oop.examples.template.core

data class Rack(
    private val owner: PlayerId,
    private val tiles: List<Tile>,
) {
    fun owner(): PlayerId = owner
    fun tiles(): List<Tile> = tiles.toList()
    fun hasTile(tile: Tile): Boolean = tile in tiles
    fun removeTiles(tilesToRemove: List<Tile>): Rack {
        val updatedTiles = tiles.toMutableList()
        tilesToRemove.forEach { tile ->
            updatedTiles.remove(tile)
        }
        return Rack(owner, updatedTiles)
    }

    fun addTiles(tilesToAdd: List<Tile>): Rack {
        return Rack(owner, tiles + tilesToAdd)
    }
}