package hwr.oop.examples.template.core

data class PlayerRack(
    private val playerId: PlayerId,
    private val tiles: List<Tile>
) {
    fun playerId() = playerId
    fun tiles() = tiles
}