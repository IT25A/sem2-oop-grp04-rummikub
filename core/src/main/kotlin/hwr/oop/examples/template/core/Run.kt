package hwr.oop.examples.template.core
class Run(val tiles: List<Tile>){
    fun value(): Int = tiles.sumOf { it.value() }
    init {
        require(tiles.map { it.color }.distinct().size == 1){
            "this Run has not the same color"
        }
        require(tiles.size >= 3) {
            "Run must have at least 3 tiles"
        }
        require(tiles.zipWithNext().all { (first, second) ->
            second.number == first.number + 1
        }) {
            "All tiles in a run must have consecutive numbers"
        }


    }

}
