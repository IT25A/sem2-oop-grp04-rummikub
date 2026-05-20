package hwr.oop.students.group4.rummikub.core

class Game (
    //private val gameId: String,
    private val pool: Pool = Pool(),
    private val rackOfPlayers: List<Rack>,
    private var currentPlayerIndex: Int = 0,
    private val currentPlayer: PlayerId = rackOfPlayers[currentPlayerIndex].owner(),

) {
    companion object {
        fun createNewGame(players: List<PlayerId>): Game {
            require(players.size in 2..4) { "Rummikub is always 2-4" }
            require(players.distinct().size == players.size) { "Players must have different names" }
            val pool = Pool()
            val racks = players.map { player -> Rack(player, pool.draw(14).toMutableList()) }
            return Game(pool, racks)
        }
    }
    //Commands
    // later implemented
    /*
    
    fun playTiles(){

    }
    fun drawTile(){

    }
    */
    
    //Queries
    fun pool() = pool
    
    fun racks() = rackOfPlayers //Added this just for the tests to work, please implement properly and fix tests in PoolTest.kt
}
