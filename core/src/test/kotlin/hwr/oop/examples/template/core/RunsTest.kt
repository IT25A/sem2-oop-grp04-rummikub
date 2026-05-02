package hwr.oop.examples.template.core
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.ValueSource
class RunsTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    fun `run value equals sum of all tile numbers`(startNumber: Int) {
        for (color in Color.entries) {
            val maxLength = 13 - startNumber + 1

            for (length in 3..maxLength) {
                val tiles = (0 until length).map { offset ->
                    Tile(color, startNumber + offset)
                }

                val run = Run(tiles)

                val expectedValue = tiles.sumOf { it.number }

                assertThat(run.value()).isEqualTo(expectedValue)
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    fun `run with any length works for any color and start number`(startNumber: Int) {
        for (color in Color.entries) {
            // maximale Länge: von startNumber bis 13
            val maxLength = 13 - startNumber + 1

            for (length in 3..maxLength) {
                // erstelle `length` aufeinanderfolgende Steine
                val tiles = (0 until length).map { offset ->
                    Tile(color, startNumber + offset) //erstelle length Steine, die alle die gleiche Farbe haben, aber fortlaufend nummeriert sind ab startNumber.
                }

                // when
                val run = Run(tiles)

                // then
                assertThat(run.tiles).hasSize(length)
                assertThat(run.tiles).allMatch { it.color == color }
                assertThat(run.tiles.first().number).isEqualTo(startNumber)
                assertThat(run.tiles.last().number).isEqualTo(startNumber + length - 1)
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `run can start at any number from 1 to 11`(startNumber: Int) {
        // Skip wenn startNumber > 11, weil dann tile3 (startNumber+2) > 13 wäre
        if (startNumber > 11) return

        // given
        val tile1 = Tile(Color.RED, startNumber)
        val tile2 = Tile(Color.RED, startNumber + 1)
        val tile3 = Tile(Color.RED, startNumber + 2)
        val tiles = listOf(tile1, tile2, tile3)

        // when
        val run = Run(tiles)

        // then
        assertThat(run.tiles.first().number).isEqualTo(startNumber)
    } // Dieser Test prüft alle möglichen Start-Punkte für eine 3er-Run (1-13

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12])
    fun `run with only 2 tiles is invalid for any start number`(startNumber: Int) {
        for (color in Color.entries) {
            val tiles = listOf(
                Tile(color, startNumber),
                Tile(color, startNumber + 1),
            )

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("at least 3")
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `run with gap in numbers is invalid`(startNumber: Int) {
        for (color in Color.entries) {
            val tiles = listOf(
                Tile(color, startNumber),
                Tile(color, startNumber + 1),
                Tile(color, startNumber + 3),
            )

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("consecutive")
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11])
    fun `run with one different color is invalid`(startNumber: Int) {
        for (correctColor in Color.entries) {
            val wrongColor = Color.entries.first { it != correctColor }

            val tiles = listOf(
                Tile(correctColor, startNumber),
                Tile(correctColor, startNumber + 1),
                Tile(wrongColor, startNumber + 2),
            )

            assertThatThrownBy {
                Run(tiles)
            }.hasMessageContaining("same color")
        }
    }

    @Test //given
    fun `runs with minimum 3 numbers `(){
        val tileRed = Tile(Color.RED, 5)
        val tileRed2 = Tile(Color.RED, 6)
        val tileRed3 = Tile(Color.RED, 7)
        val tiles = listOf(tileRed, tileRed2, tileRed3)
        //when
        val run= Run(tiles)
        //then
        assertThat(run.tiles).hasSize(tiles.size)
        assertThat(run.tiles).allMatch { it.color == Color.RED }

    }
    @Test
    fun `runs with with different colors is invalid  `() {
        val tile1 = Tile(Color.RED, 5)
        val tile2 = Tile(Color.BLUE, 6)
        val tile3 = Tile(Color.RED, 7)
        val tiles1 = listOf(tile1, tile2, tile3)
        assertThatThrownBy { Run(tiles1) }.hasMessageContaining("not the same color")
    }
    @ParameterizedTest
    @EnumSource(Color::class) //@EnumSource(Color::class) "Ruf diese Funktion einmal pro Enum-Wert auf."
    fun `run with 3 consecutive tiles works in any color`(color: Color) {
        // given
        val tile1 = Tile(color, 5)
        val tile2 = Tile(color, 6)
        val tile3 = Tile(color, 7)
        val tiles = listOf(tile1, tile2, tile3)

        // when
        val run = Run(tiles)

        // then
        assertThat(run.tiles).hasSize(tiles.size)
        assertThat(run.tiles).allMatch { it.color == color }
    }//Macht der das nur für 5, 6, 7


}