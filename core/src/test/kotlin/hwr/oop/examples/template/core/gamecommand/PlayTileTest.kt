package hwr.oop.examples.template.core.gamecommand

import hwr.oop.examples.template.core.Game
import hwr.oop.examples.template.core.GameStatus
import hwr.oop.examples.template.core.PlayerId
import hwr.oop.examples.template.core.Pool
import hwr.oop.examples.template.core.Rack
import hwr.oop.examples.template.core.Tile
import hwr.oop.examples.template.core.TileColor
import hwr.oop.examples.template.core.TileNumber
import hwr.oop.examples.template.core.Set
import hwr.oop.examples.template.core.Table
import org.junit.jupiter.api.Test
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertTrue

class PlayTileTest {
    //given
    private val player1 = PlayerId("player1")
    private val rack1 = Rack(player1, listOf(
        Tile(TileColor.BLUE, TileNumber.THIRTEEN),
        Tile(TileColor.RED, TileNumber.THIRTEEN),
        Tile(TileColor.YELLOW, TileNumber.THIRTEEN),
        Tile(TileColor.BLACK, TileNumber.THIRTEEN),
        Tile(TileColor.BLUE, TileNumber.TWELVE),
        Tile(TileColor.BLUE, TileNumber.ELEVEN),
    ))

    private val player2 = PlayerId("player2")
    private val rack2 = Rack(player2, listOf(
        Tile(TileColor.BLUE, TileNumber.ONE),
        Tile(TileColor.RED, TileNumber.ONE),
        Tile(TileColor.YELLOW, TileNumber.ONE),
        Tile(TileColor.BLACK, TileNumber.ONE),
    ))
    private val rack2Alt = Rack(player2, listOf(
        Tile(TileColor.BLUE, TileNumber.TWELVE),
        Tile(TileColor.RED, TileNumber.TWELVE),
        Tile(TileColor.YELLOW, TileNumber.TWELVE),
        Tile(TileColor.BLACK, TileNumber.TWELVE),
        Tile(TileColor.YELLOW, TileNumber.ELEVEN),
    ))

    private val set1 = Set(listOf(
        Tile(TileColor.BLUE, TileNumber.THIRTEEN),
        Tile(TileColor.RED, TileNumber.THIRTEEN),
        Tile(TileColor.YELLOW, TileNumber.THIRTEEN),
        Tile(TileColor.BLACK, TileNumber.THIRTEEN),
    ))
    private val set1Alt = Set(listOf(
        Tile(TileColor.RED, TileNumber.THIRTEEN),
        Tile(TileColor.YELLOW, TileNumber.THIRTEEN),
        Tile(TileColor.BLACK, TileNumber.THIRTEEN),
    ))
    private val set2 = Set(listOf(
        Tile(TileColor.BLUE, TileNumber.TWELVE),
        Tile(TileColor.RED, TileNumber.TWELVE),
        Tile(TileColor.YELLOW, TileNumber.TWELVE),
        Tile(TileColor.BLACK, TileNumber.TWELVE),
    ))
    private val set3 = Set(listOf(
        Tile(TileColor.BLUE, TileNumber.THIRTEEN),
        Tile(TileColor.BLUE, TileNumber.TWELVE),
        Tile(TileColor.BLUE, TileNumber.ELEVEN),
    ))

    private val invalidSet = Set(listOf(
        Tile(TileColor.BLUE, TileNumber.ONE),
        Tile(TileColor.BLACK, TileNumber.ONE),
        Tile(TileColor.BLUE, TileNumber.ONE)
    ))

    private val invalidMeld = Set(listOf(
        Tile(TileColor.BLUE, TileNumber.ONE),
        Tile(TileColor.RED, TileNumber.ONE),
        Tile(TileColor.YELLOW, TileNumber.ONE),
        Tile(TileColor.BLACK, TileNumber.ONE),
    ))

    @Test
    fun`playtile meld successful`(){
        //when
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf(set1))
        val updatedGame = game.playTiles(newTable, player1)

        assertTrue(updatedGame.rackOf(player1).isOpen())
        assertThat(updatedGame.table()).isEqualTo(newTable)
        assertThat(updatedGame.rackOf(player1).tiles()).isEqualTo(listOf<Tile>(
            Tile(TileColor.BLUE, TileNumber.TWELVE),
            Tile(TileColor.BLUE, TileNumber.ELEVEN),
        ))
    }

    @Test
    fun`playtile post-meld manipulation successful, game finished`(){
        //when
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2Alt),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable1 = Table(listOf(set1))
        val firstTurn = game.playTiles(newTable1, player1)
        val newTable2 = Table(listOf(set1, set2))
        val secondTurn = firstTurn.playTiles(newTable2, player2)
        val newTable3 = Table(listOf(set1Alt, set2, set3))
        val thirdTurn = secondTurn.playTiles(newTable3, player1)

        assertThat(thirdTurn.table()).isEqualTo(newTable3)
        assertThat(thirdTurn.rackOf(player1).tiles()).isEqualTo(listOf<Tile>())
        assertTrue(thirdTurn.rackOf(player2).isOpen())
        assertTrue(thirdTurn.rackOf(player1).isOpen())
        assertTrue(thirdTurn.isFinished())
        assertTrue(thirdTurn.winner() == player1)
        assertTrue(thirdTurn.status() == GameStatus.FINISHED)
    }

    @Test
    fun `playtile failed, player not in players`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf(set1))
        val troll = PlayerId("troll")
        assertThatThrownBy { game.playTiles(newTable, troll) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, troll) }.hasMessageContaining("is not in players")
    }

    @Test
    fun `playtile failed, player not current player`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf(set1))
        assertThatThrownBy { game.playTiles(newTable, player2) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, player2) }.hasMessageContaining("is not current player")
    }

    @Test
    fun `playtile failed, new table is empty`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf<Set>())
        assertThatThrownBy { game.playTiles(newTable, player1) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, player1) }.hasMessageContaining("New Table cannot be empty")
    }

    @Test
    fun `playtile failed, set is invalid`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf<Set>(invalidSet))
        assertThatThrownBy { game.playTiles(newTable, player1) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, player1) }.hasMessageContaining("do not form a valid group or run")
    }

    @Test
    fun `playtile failed, valid set is not in hand`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf<Set>(set2))
        assertThatThrownBy { game.playTiles(newTable, player1) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, player1) }.hasMessageContaining("not in ${player1.id()}'s rack")
    }

    @Test
    fun `playtile failed, invalid meld doesn't have enough points`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player2,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf<Set>(invalidMeld))
        assertThatThrownBy { game.playTiles(newTable, player2) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { game.playTiles(newTable, player2) }.hasMessageContaining("Initial meld requires")
    }

    @Test
    fun `playtile failed, game is finished`(){
        val game = Game(
            gameId = UUID.randomUUID(),
            gameStatus = GameStatus.IN_PROGRESS,
            playerRacks = listOf(rack1, rack2),
            currentPlayer = player1,
            table = Table(),
            pool = Pool(listOf(Tile(TileColor.BLUE, TileNumber.ONE))),
        )
        val newTable = Table(listOf(set1Alt, set3))
        val turnOne = game.playTiles(newTable, player1)
        val newTable2 = Table(listOf(set1))
        assertThatThrownBy { turnOne.playTiles(newTable2, player2) }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { turnOne.playTiles(newTable2, player2) }.hasMessageContaining("Game is finished")
    }
}