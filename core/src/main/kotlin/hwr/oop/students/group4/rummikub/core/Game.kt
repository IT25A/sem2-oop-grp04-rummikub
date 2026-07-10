package hwr.oop.students.group4.rummikub.core

import kotlinx.serialization.Serializable

@Serializable
data class Game (
    private val gameId : GameId = GameId.random(),
    private val pool: Pool,
    private val racks: List<Rack>,
    private val currentPlayer: PlayerId,
    private val board: Board = Board(),
    private val players: List<PlayerId> = racks.map { it.owner() },
    private val gameStatus: GameStatus = GameStatus.IN_PROGRESS,
    private val winner: PlayerId? = null,
    ) {
    companion object {
        fun createNewGame(
            gameId: GameId = GameId.random(),
            players: List<PlayerId>
        ): Game {
            require(players.size in 2..4) { "Rummikub is always 2-4" }
            require(players.distinct().size == players.size) { "Players must have different names" }
            val pool = Pool.createShuffledPool().toMutablePool()
            val racks = players.map { player -> Rack(player, pool.draw(14))}
            val currentPlayer = racks[0].owner()
            return Game(
                gameId = gameId,
                pool= pool.toPool(),
                racks = racks,
                currentPlayer = currentPlayer,
            )
        }
    }
    //Commands
    
    fun playTiles(newBoard: Board, player: PlayerId) : Game {
        require(gameStatus != GameStatus.FINISHED) { "Game is finished" }
        validatePlayer(player)
        newBoard.sets().all { SetType.entries.contains(it.type()) }

        val currentRack = rackOf(player)
        val newBoardTiles = newBoard.tiles()
        val oldBoardTiles = board.tiles()
        require(oldBoardTiles.isContainedIn(newBoardTiles)){ "New table is missing tiles from old table"}
        val addedTiles = newBoardTiles.toMutableList().apply { oldBoardTiles.forEach { remove(it) } }.toList()
        val points = addedTiles.sumOf { it.number().value() }

        require(addedTiles.isNotEmpty()) { "When playing tiles, new ones must be added to the board"}
        require(addedTiles.isContainedIn(currentRack.tiles())){ "Tile is not in ${player.playerId()}'s rack" }

        if (!currentRack.melded()) {
            require(points >= 30) { "Initial meld requires having 30 or more points" }
        }

        val updatedRacks = racks.map { rack ->
            if (rack.owner() == currentPlayer) {
                    rack.removeTiles(addedTiles)
            } else {
                rack
            }
        }

        return if (updatedRacks.any { it.tiles().isEmpty() }) {
            copy (
                gameId = gameId,
                board = newBoard,
                racks = updatedRacks,
                gameStatus = GameStatus.FINISHED,
                winner = currentPlayer,
            )
        } else {
            copy(
                gameId = gameId,
                board = newBoard,
                racks = updatedRacks,
                currentPlayer = nextPlayer()
            )
        }
    }

    fun drawTile (player: PlayerId): Game {
        require(gameStatus != GameStatus.FINISHED) { "Game is finished" }
        validatePlayer(player)
        require(pool.tiles().isNotEmpty()) { "Pool is empty" }

        val newPool = pool.toMutablePool()
        val drawnTile = newPool.draw(1)

        val updatedRacks: List<Rack> = racks.map { rack ->
            if (rack.owner() == player) {
                rack.addTiles(drawnTile)
            }   else {
                rack
            }
        }
        return copy (
            gameId = gameId,
            pool = newPool.toPool(),
            racks = updatedRacks,
            currentPlayer = nextPlayer()
        )
    }

    fun validatePlayer(player: PlayerId) {
        require(player in players()){"Player is not in this game"}
        require(player == currentPlayer) { "Its not ${player.playerId()}'s turn" }
    }

    fun List<Tile>.isContainedIn(other: List<Tile>): Boolean {
        return this.groupingBy { it }
            .eachCount()
            .all { (element, requiredCount) ->
                other.count { it == element } >= requiredCount
            }
    }

    //Queries
    fun id() = gameId
    fun pool() = pool
    fun players(): List<PlayerId> {
        return racks.map { it.owner()  }
    }
    fun rackOf(playerId: PlayerId): Rack {
        require(playerId in players()){"Player is not in this game"}
        return racks.find{ it.owner() == playerId }!!
    }
    fun racks() = racks //Added this just for the tests to work, please implement properly and fix tests in PoolTest.kt
    fun board() = board
    fun currentPlayer() = currentPlayer
    fun nextPlayer(): PlayerId = players[((players.indexOf(currentPlayer) + 1) % players().size)]
    fun status() = gameStatus
    fun winner() = winner
}
