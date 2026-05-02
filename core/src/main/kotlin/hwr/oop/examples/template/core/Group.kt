package hwr.oop.examples.template.core

class Group(val tiles: List<Tile>)
{

    fun value(): Int = tiles.sumOf {
        it.value()
    }


    init {
        require(tiles.size == 3 || tiles.size == 4) {
            "Group must have 3 or 4 tiles"
        }
        require(tiles.map { it.number }.distinct().size == 1) {
            "All tiles in a group must have the same number"
        }
        require(tiles.map { it.color }.distinct().size == tiles.size) {
            "All tiles in a group must have different colors"
        }
    }
}

//require(BEDINGUNG) { "FEHLERMELDUNG" }require(BEDINGUNG) { "GRUND-WARUM-NICHT" }