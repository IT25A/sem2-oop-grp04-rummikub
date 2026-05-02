package hwr.oop.examples.template.core

data class PlayerRack(
    private val player: PlayerId,
    private val tiles: List<Tile>,
) {
    fun player(): PlayerId = player
    fun tiles(): List<Tile> = tiles
}