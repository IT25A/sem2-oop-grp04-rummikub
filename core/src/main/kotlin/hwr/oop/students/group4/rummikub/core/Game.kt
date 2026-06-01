package hwr.oop.students.group4.rummikub.core

data class Game (
    //private val gameId: String,
    private val pool: Pool,
    private val rackOfPlayers: List<Rack>,
    private var currentPlayerIndex: Int = 0,
    private val currentPlayer: PlayerId = rackOfPlayers[currentPlayerIndex].owner(),
    private val board: Board = Board(),

) {
    companion object {
        fun createNewGame(players: List<PlayerId>): Game {
            require(players.size in 2..4) { "Rummikub is always 2-4" }
            require(players.distinct().size == players.size) { "Players must have different names" }
            val newPool = Pool.createShuffledPool().toMutablePool()
            val racks = players.map { player -> Rack(player, newPool.draw(14))}
            return Game(newPool.toPool(), racks)
        }
        //TODO: LoadGame / GetGame (gameState: GameState): Game {}
    }
    //Commands
    
    fun playTiles(newBoard: Board, player: PlayerId) : Game {
        require(newBoard.sets().all { SetType.entries.contains(it.type()) }) {"A set was not valid"}
        validatePlayer(player)

        val newBoardTiles = newBoard.tiles()
        val oldBoardTiles = board.tiles()
        oldBoardTiles.forEach { oldTile -> require(newBoardTiles.contains(oldTile)) }

        val addedTiles = newBoardTiles.toMutableList().apply { oldBoardTiles.forEach { remove(it) } }.toList()

        val updatedRacks = rackOfPlayers.map { rack ->
            if (rack.owner() == currentPlayer) {
                rack.removeTiles(addedTiles)
            } else {
                rack
            }
        }

        return copy(
            board = newBoard,
            rackOfPlayers = updatedRacks,
            currentPlayerIndex = nextPlayerIndex()
        )
    }

    fun drawTile (player: PlayerId): Game {
        validatePlayer(player)
        require(pool.tiles().isNotEmpty()) { "Pool is empty" }
        val newPool = pool.toMutablePool()
        val drawnTile = newPool.draw(1)

        val updatedRacks: List<Rack> = rackOfPlayers.map { rack ->
            if (rack.owner() == player) {
                rack.addTiles(drawnTile)
            }   else {
                rack
            }
        }
        return copy (
            pool = newPool.toPool(),
            rackOfPlayers = updatedRacks,
            currentPlayerIndex = nextPlayerIndex()
            // currentPlayer does not get set, why?
        )
    }

    fun validatePlayer(player: PlayerId) {
        require(player in players()){"Player is not in this game"}
        require(player == currentPlayer) { "Its not ${player.playerId()}'s turn" }
    }

    //Queries
    fun pool() = pool
   
    fun players(): List<PlayerId> {
        return rackOfPlayers.map { it.owner()  }
    }
   
    fun rackOfPlayer(playerId: PlayerId): Rack {
        validatePlayer(playerId)
        return racks().find{ it.owner() == playerId }!!
    }
    
    fun racks() = rackOfPlayers //Added this just for the tests to work, please implement properly and fix tests in PoolTest.kt
    
    fun board() = board
    
    fun currentPlayer() = currentPlayer

    fun nextPlayerIndex() = ((currentPlayerIndex + 1) % players().size)
}
