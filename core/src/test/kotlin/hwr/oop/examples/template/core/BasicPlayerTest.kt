package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BasicPlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["player1", "player2", "player3", "justarandomstring"])
    fun `player id`(strings: String) {
        // given
        val playerId = PlayerId(strings)

        // when
        // then
        assertThat(playerId.toString()).isEqualTo("PlayerId(value=$strings)")
    }

    @Test
    fun `player rack`() {
        // given
        val playerRack = PlayerRack(PlayerId(""), listOf())
        // when
        // then
    }
}