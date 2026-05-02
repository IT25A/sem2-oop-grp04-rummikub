package hwr.oop.examples.template.core
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
class TileTest {
    @Test
    fun `tile can be created in all 52 combinations`() {
        for (color in Color.entries) {
            for (number in 1..13) {
                val tile = Tile(color, number)
                assertThat(tile.color).isEqualTo(color)
                assertThat(tile.number).isEqualTo(number)
            }
        }
    }
}