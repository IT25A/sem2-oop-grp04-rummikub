package hwr.oop.examples.template.core

import kotlin.collections.Set

class Game (
    private val racksOfPlayers: List<Rack>,
    private val players: List<PlayerId> = racksOfPlayers.map { it.owner() },
    private val pool: PoolMutable,
    private val board: BoardMutable = Board().toMutableBoard(),
    private val currentPlayerIndex: Int = 0,
    private val openedPlayers: Set<PlayerId> = emptySet(),
) {
    companion object {
        private const val INITIAL_TILES = 14
        private const val MELD_THRESHOLD = 30

        fun createNewGame(
            players: List<PlayerId>
        ): Game {
            require(players.size in 2..4) { "Rummikub has to have 2-4 players" }
            require(players.distinct().size == players.size) { "Player IDs must be unique" }

            val pool = Pool.createShuffledPool().toMutablePool()
            val racks = dealRacks(players, pool)
            return Game(racksOfPlayers = racks, pool = pool)
        }

        private fun dealRacks(
            players: List<PlayerId>,
            pool: PoolMutable,
        ): List<Rack> {
            val playerRackTiles = players.associateWith { mutableListOf<Tile>() }
            repeat (INITIAL_TILES) {
                players.forEach { player ->
                    playerRackTiles.getValue(player).addAll(pool.draw())
                }
            }
            return players.map { player -> Rack(player, playerRackTiles.getValue(player)) }
        }


    }

// Queries
    fun rackOf(player: PlayerId): Rack {
        require(player in players) { "Player $player is not part of this game" }
        return racksOfPlayers.find { it.owner() == player }!!
    }

    fun currentPlayer(): PlayerId = players[currentPlayerIndex]

    fun pool(): PoolMutable = pool

    fun board(): Board = board

    fun hasOpened(player: PlayerId): Boolean = player in openedPlayers

    fun isFinished(): Boolean = racksOfPlayers.any { it.tiles().isNotEmpty() }

    fun winner(): PlayerId? = racksOfPlayers.firstOrNull { it.tiles().isEmpty() }?.owner()

    fun placeSet(player: PlayerId, tiles: List<Tile>): hwr.oop.examples.template.core.Set {
        requireNotFinished()
        requireCurrentPlayer(player)

    }
    fun placeMeld(player: PlayerId, tiles: List<Tile>): Game {
        requireCurrentPlayer(player)
        require(MeldValidator.isValid(tiles)) {
            "Tiles do not form a valid meld: $tiles"
        }

        val meldPoints = MeldValidator.points(tiles)
        val isAlreadyOpened = player in openedPlayers
        if (!isAlreadyOpened) {
            require(meldPoints >= MELD_THRESHOLD) {
                "Opening meld must total at least $MELD_THRESHOLD points (current points: $meldPoints)"
            }
        }
        val rack = rackOf(player)
        tiles.forEach { tile ->
            require(rack.hasTile(tile)) {"Tile $tile is not in $player rack"}
        }
        board.addMeld(meld)
    }

}