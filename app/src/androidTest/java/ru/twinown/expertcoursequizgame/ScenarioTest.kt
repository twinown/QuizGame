package ru.twinown.expertcoursequizgame

import android.app.GameState
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

//запускаем нужный эмулятор
@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    //какое активити запускать для юайтеста
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage:GamePage

    @Before
    //before означает, что сначала будет запущен он, потом уже тесты
    fun setUp(){
        //page -объект - аналог нашего экрана.но он будет отличаться от другого пейдж-объекта
        //когда перейдём на другой вопрос.поэтому var
        gamePage = GamePage(question = "What color is the sky?",answer = listOf(
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

        gamePage.clickFirstChoise()
        gamePage.assertFirstChoiseMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    //джава - дока
    /**
     * Test case number 2 QGTC-02
     */
    @Test
    fun caseNumber2() {
        //page -объект - аналог нашего экрана.но он будет отличаться от другого пейдж-объекта
        //когда перейдём на другой вопрос.поэтому var

        //высокоуровневые тесты, как в фигме в тесткейсе номер2
        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoise()
        gamePage.assertFirstChoiseMadeState()


        gamePage.clickSecondChoise()
        gamePage.assertSecondChoiseMadeState()



        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()

        gamePage = GamePage(question = "What color is the grass?",answer = listOf(
            "green","blue","yellow","red"
        ))
        gamePage.assertAskedQuestionState()

    }
}