package hwr.oop.students.group4.rummikub.core

data class Game (
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
            val racks = players.map { player -> Rack(player, pool.draw(14))}
            return Game(pool, racks)
        }
    }
    //Commands
    // later implemented
    /*
    
    fun playTiles(){

    */

    fun drawTile (player: PlayerId): Game {
        require( player in players() )
        require(player == currentPlayer) { "Its not ${player.playerId()}'s turn"}
        require(pool.tiles().isNotEmpty()) { "Pool is empty" }
        val drawnTile = pool.draw(1)

        val updatedRacks: List<Rack> = rackOfPlayers.map { rack ->
            if (rack.owner() == player) {
                rack.addTiles(drawnTile)
            }   else {
                rack
            }
        }
        return copy (
            pool = pool,
            rackOfPlayers = updatedRacks,
            currentPlayerIndex = (currentPlayerIndex + 1) % players().size
        )
    }

    //Queries
    fun pool() = pool
   
    fun players(): List<PlayerId> {
        return rackOfPlayers.map { it.owner()  }
    }
   
    fun rackOfPlayer(playerId: PlayerId): Rack? = rackOfPlayers.find { it.owner() == playerId }
    fun racks() = rackOfPlayers //Added this just for the tests to work, please implement properly and fix tests in PoolTest.kt
}
