package hwr.oop.examples.template.core
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GroupTest {


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group value equals number times 3 for any 3 colors`(number: Int) {
        for (excludedColor in Color.entries) {
            val colors = Color.entries.filter { it != excludedColor }

            val tiles = colors.map { color ->
                Tile(color, number)
            }

            val group = Group(tiles)

            assertThat(group.value()).isEqualTo(number * 3)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with any 3 different colors works for any number`(number: Int) {
        for (excludedColor in Color.entries) {
            val colors = Color.entries.filter { it != excludedColor }

            val tiles = colors.map { color ->
                Tile(color, number)
            }

            val group = Group(tiles)

            assertThat(group.tiles).hasSize(3)
            assertThat(group.tiles).allMatch { it.number == number }
            assertThat(group.tiles.map { it.color }.distinct()).hasSize(3)
        }
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group value equals number times 4 for all colors`(number: Int) {
        val tiles = Color.entries.map { color ->
            Tile(color, number)
        }




    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with 3 tiles works for any number (Red Blue Black)`(number: Int) {
        val tiles = listOf(
            Tile(Color.RED, number),
            Tile(Color.BLUE, number),
            Tile(Color.BLACK, number),
        )
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(3)
        assertThat(group.tiles).allMatch { it.number == number }   // ← 3 steine
        assertThat(group.tiles.map { it.color }.distinct()).hasSize(3) //3 unterschiedliche Farben
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with 3 tiles works for any number (Orange-Blue-Black)`(number: Int) {
        val tiles = listOf(
            Tile(Color.ORANGE, number),
            Tile(Color.BLUE, number),
            Tile(Color.BLACK, number),
        )
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(3)
        assertThat(group.tiles).allMatch { it.number == number }
        assertThat(group.tiles.map { it.color }.distinct()).hasSize(3)// ← 3 steine 3 farben
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with 3 tiles works for any number (Orang-Red-Black)`(number: Int) {
        val tiles = listOf(
            Tile(Color.ORANGE, number),
            Tile(Color.RED, number),
            Tile(Color.BLACK, number),
        )
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(3)
        assertThat(group.tiles).allMatch { it.number == number }   // ← 3 steine
        assertThat(group.tiles.map { it.color }.distinct()).hasSize(3)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with 3 tiles works for any number (Orange, Blue, Black) `(number: Int) {
        val tiles = listOf(
            Tile(Color.ORANGE, number),
            Tile(Color.BLUE, number),
            Tile(Color.BLACK, number),
        )
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(3)
        assertThat(group.tiles).allMatch { it.number == number } // ← 3 steine
        assertThat(group.tiles.map { it.color }.distinct()).hasSize(3)

    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with 4 tiles works for any number`(number: Int) {
        val tiles = listOf(
            Tile(Color.RED, number),
            Tile(Color.BLUE, number),
            Tile(Color.BLACK, number),
            Tile(Color.ORANGE, number),
        )
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(4)
        assertThat(group.tiles).allMatch { it.number == number }   // ← 4 steine
        assertThat(group.tiles.map { it.color }.distinct()).hasSize(4)
    }


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with only 2 tiles is invalid for any number`(number: Int) {
        // given
        val tileRed = Tile(Color.RED, number)
        val tileBlue = Tile(Color.BLUE, number)

        // when & then
        assertThatThrownBy {
            Group(listOf(tileRed, tileBlue))
        }.hasMessageContaining("3 or 4 tiles")
    }
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with one different number is invalid for any number and any 3 colors`(number: Int) {
        val differentNumber = if (number == 13) 12 else number + 1

        for (excludedColor in Color.entries) {
            val colors = Color.entries.filter { it != excludedColor }

            val tiles = listOf(
                Tile(colors[0], number),
                Tile(colors[1], number),
                Tile(colors[2], differentNumber)
            )

            assertThatThrownBy {
                Group(tiles)
            }.hasMessageContaining("same number")
        }
    }


    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13])
    fun `group with duplicate of any color is invalid`(number: Int) {
        for (duplicateColor in Color.entries) {
            val otherColor = Color.entries.first { it != duplicateColor }

            val tile1 = Tile(duplicateColor, number)
            val tile2 = Tile(duplicateColor, number)
            val tile3 = Tile(otherColor, number)//dieselbe Nummer aber farbe 2X

            assertThatThrownBy {
                Group(listOf(tile1, tile2, tile3))
            }.hasMessageContaining("different colors")
        }
        //Der äußere Parameterized-Test schickt nacheinander Zahlen 1-13 rein.
        //Pro Zahl läuft intern eine Schleife durch alle 4 Farben
        //Pro Farbe wird eine Group mit dieser Farbe doppelt + einer anderen Farbe erstellt - und geprüft, ob das (richtigerweise) crasht.
    }



    @Test
    fun ` group with three Tiles with the same number`()
    { //given
        val tileRed = Tile(Color.RED, 3)
        val tileBlack = Tile(Color.BLACK, 3)
        val tileBlue = Tile(Color.BLUE, 3)
        val tiles = listOf(tileRed, tileBlack, tileBlue)

        //when
        val group = Group(tiles)
        assertThat(group.tiles).hasSize(tiles.size)
        assertThat(group.tiles).allMatch { it.number == 3 }
    }

    //wenn zwei dasselbe zahl haben reichts nicht
    @Test
    fun ` group with two Tiles with same number`()
    {
        val tileRed = Tile(Color.RED, 3)
        val tileBlue = Tile(Color.BLUE, 3)
        //THEN assertThatThrownBy prüft fehler
        assertThatThrownBy { Group(listOf(tileRed, tileBlue))
        }.hasMessageContaining("3 or 4 tiles")

    }
    @Test //when
    fun ` group with three Tiles with different number`() {
        val tileRed = Tile(Color.RED, 3)
        val tileBlue = Tile(Color.BLUE, 1)
        val tileBlack = Tile(Color.BLACK, 2)
        //then
        assertThatThrownBy {
            Group(listOf(tileBlack, tileBlue,tileRed))
        }.hasMessageContaining("different color but Same Numbers")
    }
    @Test
    fun ` group with multiple Tiles with duplicates Color `()
    {
        val tileRed1 = Tile(Color.RED, 5)
        val tileRed2 = Tile(Color.RED, 5)
        val tileBlue = Tile(Color.BLUE, 5)
        assertThatThrownBy {
            Group(listOf(tileRed1, tileRed2, tileBlue))
        }.hasMessageContaining("Same Number but duplicates colors ")
        }
    }






