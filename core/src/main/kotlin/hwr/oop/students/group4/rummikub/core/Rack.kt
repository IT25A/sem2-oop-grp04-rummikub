package hwr.oop.students.group4.rummikub.core

class Rack(
    private val playerId: PlayerId,
    private val tiles: MutableList<Tile>
) {
    fun owner() = playerId
    fun tiles() = tiles.toList()

    // TODO: add initial move completed (melded player), true/false toggle
    // TODO: add(), remove() tile
}
