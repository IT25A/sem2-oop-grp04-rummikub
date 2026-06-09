package hwr.oop.examples.template.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerIdTests {
    //given
    val name: String = "name"

    @Test
    fun `wraps value correctly`() {
        //when
        val playerId: PlayerId = PlayerId(name)
        //then
        assertThat(playerId).isExactlyInstanceOf(PlayerId::class.java)
        assertThat(playerId.id()).isEqualTo(name)
    }

    @Test
    fun `two instances with same string are equal` () {
        assertThat(PlayerId("player")).isEqualTo(PlayerId("player"))
    }

    @Test
    fun `two instances with different string are not equal` () {
        assertThat(PlayerId("player")).isNotEqualTo(PlayerId("troll"))
    }


}