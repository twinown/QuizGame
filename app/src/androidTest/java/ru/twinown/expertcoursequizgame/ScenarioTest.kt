package ru.twinown.expertcoursequizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.twinown.expertcoursequizgame.game.GamePage

//запускаем нужный эмулятор
@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    //какое активити запускать для юайтеста
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    //когда перейдём на другой вопрос.поэтому var
    private lateinit var gamePage: GamePage

    @Before
    //before означает, что сначала будет запущен он, потом уже тесты
    //инициализация гам пейджа
    fun setUp(){
        //page -объект - аналог нашего экрана.но он будет отличаться от другого пейдж-объекта

        gamePage = GamePage(
            question = "What color is the sky?",
            choices = listOf(
            "blue","green","red","yellow"
        ))
    }

    //джава - дока
    /**
     * Test case number 1 QGTC-01
     */
    @Test
    fun caseNumber1() {
        //высокоуровневые тесты, как в фигме в тесткейсе номер1
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    //джава - дока
    /**
     * Test case number 2 QGTC-02
     */
    @Test
    fun caseNumber2() {

        //высокоуровневые тесты, как в фигме в тесткейсе номер2
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()


        gamePage.clickSecondChoice()
        gamePage.assertSecondChoiceMadeState()



        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(
            question = "What color is the grass?", choices = listOf(
            "green","blue","yellow","red"
        ))
        gamePage.assertAskedQuestionState()

    }
}