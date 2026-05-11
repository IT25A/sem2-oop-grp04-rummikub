package hwr.oop.students.group4.rummikub.core

class Game (
    private val pool: Pool,
) {
    fun pool() = pool

    companion object {
        fun startNewGame(): Game{
            val newTiles = (1..2).flatMap {
                TileNumber.entries.flatMap { number ->
                    TileColor.entries.map { color ->
                        Tile(color, number)
                    }
                }
            }.shuffled()
            val pool = Pool(newTiles.toMutableList())
            return Game(pool)
        }
    }
}
