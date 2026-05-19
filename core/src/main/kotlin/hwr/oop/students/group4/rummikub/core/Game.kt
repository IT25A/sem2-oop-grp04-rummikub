package hwr.oop.students.group4.rummikub.core

class Game (
    private val gameId: String,
    private val pool: Pool = Pool(),
    private val players: List<PlayerId>,
    private val rackOfPlayers: List<Rack>,

) {
    fun pool() = pool

}
