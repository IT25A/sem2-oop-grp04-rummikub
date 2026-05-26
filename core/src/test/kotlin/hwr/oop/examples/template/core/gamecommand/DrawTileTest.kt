package hwr.oop.examples.template.core.gamecommand
import hwr.oop.examples.template.core.Game
import hwr.oop.examples.template.core.GameStatus
import hwr.oop.examples.template.core.PlayerId
import hwr.oop.examples.template.core.Pool
import hwr.oop.examples.template.core.Rack
import hwr.oop.examples.template.core.Table
import hwr.oop.examples.template.core.Tile
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.UUID
import java.util.stream.Stream

class DrawTileTest {
    companion object {
        private val player1 = PlayerId("player1")
        private val player2 = PlayerId("player2")
        private val player3 = PlayerId("player3")
        private val player4 = PlayerId("player4")

        private const val POOL_SIZE_WITHOUT_JOKER   = 104
        private const val TILES_PER_PLAYER          = 14

        @JvmStatic
        fun playerCombinations(): Stream<List<PlayerId>> = Stream.of(
            listOf(player1, player2),
            listOf(player1, player2, player3),
            listOf(player1, player2, player3, player4)
        )
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `DrawTile command successful, `(players: List<PlayerId>) {
        // when
        val game = Game.createNewGame(players = players)
        val currentPlayer = game.currentPlayer()
        val rack = game.rackOf(currentPlayer)
        val poolTopTile = game.pool().tiles().first()

        val updatedGame = game.drawTile(currentPlayer)
        val updatedRack = updatedGame.rackOf(currentPlayer)

        assertThat { rack.tiles().size - updatedRack.tiles().size == 1 }
        assertThat {poolTopTile in updatedRack.tiles()}
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `DrawTile, player not in players exception`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        //then
        assertThatThrownBy {game.drawTile(PlayerId("troll")) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {game.drawTile(PlayerId("troll")) }.hasMessageContaining("is not in players")
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `DrawTile, player not current player exception`(players: List<PlayerId>) {
        val game = Game.createNewGame(players = players)
        //then
        assertThatThrownBy {game.drawTile(game.nextPlayer()) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {game.drawTile(game.nextPlayer()) }.hasMessageContaining("is not current player")
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `DrawTile, pool is empty exception`(players: List<PlayerId>) {
        //when
        val playerMap = players.associateWith { _-> mutableListOf<Tile>() }
        val racks = players.map { player ->
            val rackTiles = playerMap.getValue(player)
            Rack(player, rackTiles)
        }
        val game = Game(
            UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = racks,
            currentPlayer = players.first(),
            pool = Pool(listOf()),
            table = Table()
        )
        //then
        assertThatThrownBy {game.drawTile(game.currentPlayer()) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {game.drawTile(game.currentPlayer()) }.hasMessageContaining("is empty")
    }

    @ParameterizedTest
    @MethodSource("playerCombinations")
    fun `DrawTile, game is finished`(players: List<PlayerId>) {
        //when
        val playerMap = players.associateWith { _-> mutableListOf<Tile>() }
        val racks = players.map { player ->
            val rackTiles = playerMap.getValue(player)
            Rack(player, rackTiles)
        }
        val game = Game(
            UUID.randomUUID(),
            gameStatus = GameStatus.FINISHED,
            playerRacks = racks,
            currentPlayer = players.first(),
            pool = Pool(listOf()),
            table = Table()
        )
        //then
        assertThatThrownBy {game.drawTile(game.currentPlayer()) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy {game.drawTile(game.currentPlayer()) }.hasMessageContaining("Game is finished")
    }
}