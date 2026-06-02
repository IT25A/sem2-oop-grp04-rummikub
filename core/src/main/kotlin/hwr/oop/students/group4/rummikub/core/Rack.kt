package hwr.oop.students.group4.rummikub.core

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
        val playerTiles = tiles.toMutableList()

        if ( playerTiles.removeAll(tilesToRemove)) {
            return copy(
                playerId= owner(),
                tiles = playerTiles,
                melded = true)
        } else {
            throw IllegalStateException("Player rack does not contain $tilesToRemove")
        }
    }

    fun addTiles(tilesToAdd: List<Tile>): Rack {
        return copy(
            playerId = owner(),
            tiles = (tilesToAdd + tiles)
        )
    }
}
