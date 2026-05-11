package hwr.oop.students.group4.rummikub.core

data class Pool(
    private val tiles: MutableList<Tile>,
) {
    fun tiles() = tiles
    fun draw(): Tile = tiles.removeFirst()
  }
