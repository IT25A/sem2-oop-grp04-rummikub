package hwr.oop.students.group4.rummikub.core

data class Rack(
    private val playerId: PlayerId,
    private val tiles: MutableList<Tile>,
    private var melded: Boolean = false

) {
    // Query
    fun owner() = playerId
    fun tiles() = tiles.toList()
    fun melded() = melded
    
    // Commands
    fun removeTiles(tilesToRemove: List<Tile>) {
        // will be called after all sets are checked if exist in rack and valid point amount
        melded = true
        tiles.removeAll(tilesToRemove)
    }

    fun addTiles(tilesToAdd: List<Tile>): Rack {
        return copy(
            playerId = owner(),
            tiles = (tilesToAdd + tiles).toMutableList()
        )
    }
}
