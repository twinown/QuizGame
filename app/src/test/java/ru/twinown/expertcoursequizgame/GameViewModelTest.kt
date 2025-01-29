package ru.twinown.expertcoursequizgame

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel()
    }

    /**
     * Test case number 1 QGTC-01
     */
    @Test
    fun caseNumber1() {
        //actual - тот, что получился по коду
        //а ты передаёшь ожидаемый..то есть говоришь себе : я ожидаю вот это ("это" передаёшь в скобках)
        var actual: GameUiState =
            viewModel.init() //видишь, это реальное значение из вмки,что должно работать
        var expected: GameUiState = GameUiState.AskedQustion( //чему оно должно быть равно, в итоге
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)
    }

    /**
     * Test case number 2 QGTC-02
     */
    @Test
    fun caseNumber2() {

    }
}