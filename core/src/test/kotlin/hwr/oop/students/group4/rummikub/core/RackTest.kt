package hwr.oop.students.group4.rummikub.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RackTest {

    @Test
    fun `rack owner exists`() {
        // given
        val pool = Pool()
        val initTiles = (1..14).map { pool.draw() }.toMutableList()

        val playerId1 = PlayerId("player1")

        // when
        val rack = Rack(playerId1, initTiles)

        // then
        assertThat(rack.owner().toString()).isEqualTo("PlayerId(playerId=player1)")
        assertThat(rack.tiles()).containsExactlyInAnyOrderElementsOf(initTiles)
    }
}