package hwr.oop.examples.template.core
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
class PlayerTest {
    @Test
    fun `new player has not opened yet`() {
        val player = Player("Alice")
        assertThat(player.hasOpened()).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 11, 12, 13])
    fun `player can open with any 3-tile group of value 10 or higher`(number: Int) {
        for (excludedColor in Color.entries) {
            val player = Player("Test")
            val colors = Color.entries.filter { it != excludedColor }  // 3 verbleibende Farben
            val tiles = colors.map { Tile(it, number) }
            val group = Group(tiles)

            player.open(listOf(group))

            assertThat(player.hasOpened()).isTrue()
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [8, 9, 10, 11, 12, 13])
    fun `player can open with 4-tile group of value 8 or higher`(number: Int) {
        val player = Player("Test")
        val tiles = Color.entries.map { Tile(it, number) }   // alle 4 Farben
        val group = Group(tiles)

        player.open(listOf(group))

        assertThat(player.hasOpened()).isTrue()
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9])
    fun `player cannot open with any 3-tile group below 30 points`(number: Int) {
        for (excludedColor in Color.entries) {
            val player = Player("Test")
            val colors = Color.entries.filter { it != excludedColor }
            val tiles = colors.map { Tile(it, number) }
            val group = Group(tiles)

            assertThatThrownBy {
                player.open(listOf(group))
            }.hasMessageContaining("30 points")
        }
    }


}