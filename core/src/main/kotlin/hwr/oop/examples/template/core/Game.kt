package hwr.oop.examples.template.core

class Game(
    private val racks: List<PlayerRack>,
    private val players: List<PlayerId> = racks.map{ it.player() },
) {
    companion object {
        fun create(players: List<PlayerId>): Game {
            require(players.size > 1 && players.size < 5) { "Rummikub can only be played with 2-4 Players" }
            val pool = Pool.createShuffledPool().toMutablePool()
            val racks = fillRacks(players, pool)
            return Game(racks = racks)
        }

        private fun fillRacks(
            players: List<PlayerId>,
            pool: MutablePool
        ): List<PlayerRack> {
            val playerTiles = players.associateWith { _ -> mutableListOf<Tile>() }
            for (i in 1..14) {
                players.forEach { player ->
                    val rackTiles = playerTiles.getValue(player)
                    val tileDrawn = pool.draw()
                    rackTiles.add(tileDrawn)
                }
            }
            val racks = players.map { player ->
                val rackTiles = playerTiles.getValue(player)
                PlayerRack(player, rackTiles)
            }
            return racks
        }
    }

    fun rackOf(player: PlayerId): PlayerRack {
        require(player in players) { "Player $player not in players" }
        return racks.find {it.player() == player}!!
    }
}