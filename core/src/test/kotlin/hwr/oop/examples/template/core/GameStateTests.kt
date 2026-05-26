package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameStateTests {
    @Test
    fun `all game statuses exist`() {
        // given
        val statuses = GameStatus.entries
        // when
        // then
        assertThat(statuses).containsExactlyInAnyOrder(
            GameStatus.IN_PROGRESS,
            GameStatus.FINISHED
        )
    }
}