package hwr.oop.examples.template.core

object MeldValidator {
    fun isValid(tiles: List<Tile>): Boolean {
        if (tiles.size < 3) return false
        return try {
            val set = Set(tiles)
            set.type() == SetType.GROUP || set.type() == SetType.RUN
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun points(tiles: List<Tile>): Int {
        return tiles.sumOf { it.number().points() }
    }

    fun points(meld: Meld): Int {
        return meld.sets().sumOf { it.points() }
    }

    fun validateTilesInRack(meld: Meld, rack: Rack): Boolean {
        val allTiles = meld.sets().flatMap { it.tiles() }
        return allTiles.all { rack.hasTile(it) }
    }
}