package ru.twinown.expertcoursequizgame

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test



class GameViewModelTest {

    private lateinit var viewModel: GameViewModel

    @Before
    fun setup() {
        viewModel = GameViewModel(repository = FakeRepository())
    }

    /**
     * Test case number 1 QGTC-01
     */
    //как в юай тесте _прям по нему идёшь
    @Test
    fun caseNumber1() {
        //actual - тот, что получился по коду
        //а ты передаёшь ожидаемый..то есть говоришь себе : я ожидаю вот это ("это" передаёшь в скобках)
        //на ините репозиторием отдаются данные
        var actual: GameUiState =
            viewModel.init() //видишь, это реальное значение из вмки,что должно работать
        var expected: GameUiState = GameUiState.AskedQuestion( //чему оно должно быть равно, в итоге
            //вот ожидаемое - это типо что должно быть на инишале твоём
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

         actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade( //это название идёт из фигмы около под стейтами твоих экранов
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected,actual)

        //некст у нас ни на что не влияет , поэтому мы можем его и не указыывать здесь, но потом он будет
        actual = viewModel.check()
            expected = GameUiState.AnswerChecked(
                question = "q1",
                choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"),
                )
            )
        assertEquals(expected,actual)

    }

    /**
     * Test case number 2 QGTC-02
     */
    @Test
    fun caseNumber2() {
        var actual: GameUiState =
            viewModel.init() //видишь, это реальное значение из вмки,что должно работать
        var expected: GameUiState = GameUiState.AskedQuestion( //чему оно должно быть равно, в итоге
            //вот ожидаемое - это типо что должно быть на инишале твоём
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.NotAvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected,actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected,actual)


        //клик на второй и третий добоавили тут, в фигме этого нет
        actual = viewModel.chooseThird()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.AvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected,actual)

        actual = viewModel.chooseFourth()
        expected = GameUiState.ChoiceMade(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.AvailableToChoose(text = "c1"),
                ChoiceUiState.AvailableToChoose(text = "c2"),
                ChoiceUiState.AvailableToChoose(text = "c3"),
                ChoiceUiState.NotAvailableToChoose(text = "c4"),
            )
        )
        assertEquals(expected,actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            question = "q1",
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct(text = "c1"),
                ChoiceUiState.NotAvailableToChoose(text = "c2"),
                ChoiceUiState.NotAvailableToChoose(text = "c3"),
                ChoiceUiState.Incorrect(text = "c4")
            )
        )
        assertEquals(expected,actual)

        actual = viewModel.chooseNext()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        assertEquals(expected,actual)
    }

}
private class FakeRepository:GameRepository{

    //в репе хранятся чистые данные
    //репа получает юзерские данные,либо отдаёт свои_командует ей вьюмоделька
    private val list :List<QuestionAndChoices> = listOf(
        QuestionAndChoices(question = "q1",choices = listOf("c1", "c2", "c3", "c4"),
            correctIndex=0), //индекс правильного ответа
        QuestionAndChoices(question = "q2", choices = listOf("cd1", "cd2", "cd3", "cd4"),
            correctIndex = 0)
    )

    private var index = 0
    //метод отдачи данных с репы//текущих вопроса и ответов
    override fun questionAndChoices():QuestionAndChoices{
        return list[index]
    }

    private var userChoiceIndex = -1

    //чё выбрал юзер//сохраняем это в репу
    override fun saveUserChoice(index:Int){
        userChoiceIndex = index
    }

    //от репы отдаём обратно,видишь
    override fun check():CorrectAndUserChoiceIndexes{
        return CorrectAndUserChoiceIndexes(correctIndex= questionAndChoices().correctIndex,
            userChoiceIndex = userChoiceIndex)
    }

    override fun next(){
        //типо сбрасываем значения
        userChoiceIndex = -1
        index++
        if (index== list.size){
            index=0
        }
    }
}
