package ru.twinown.expertcoursequizgame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.Matcher
import ru.twinown.expertcoursequizgame.R


//hamcrest - библиотека для проверки условий (в тестах оно типо часть эспрессо)
//Espresso - билиотека для взаимодействия с Ui


class GamePage(
    question: String,
    choices: List<String>
) {

    //эти мачтеры нужны для более точного поиска элементов в Ui - тестах
    //созданиеп матчера

    /* Родительский элемент — это контейнер (например, LinearLayout, ConstraintLayout и т.д.), который содержит другие
       элементы интерфейса.Дочерний элемент — это элемент, который находится внутри родительского контейнера.
       Например, кнопка или текстовое поле, добавленные внутрь LinearLayout, являются его дочерними элементами.
   */

    //этот матчер ищет дочерний элемент , который находится внутри родительского элемента  с айди rootLayout
    // containerIdMatcher находит элементы, расположенные внутри конкретного контейнера с ID rootLayout.
    //найди элемент внутри контейнера(ЛинеарЛэйаут, констрэйт), чей родитель имеет id R.id.rootLayout
    //всё в одном контейненре, потому вот так
    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))

    //этот матчер находит дочерний элемент , чей родитель - это LinearLayout
    // classTypeMatcher находит элементы, находящиеся внутри любого родителя типа LinearLayout.
    //isAssignableFrom(LinearLayout::class.java) — проверяет, что родительский элемент относится к классу
    // LinearLayout или его подклассу.
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))


    //использование пример
    //onView(containerIdMatcher)    // Находим элемент по первому матчеру
    //    .check(matches(isDisplayed()))  // Проверяем, что он отображается

    //onView(classTypeMatcher)      // Находим элемент по второму матчеру
    //    .perform(click())         // Выполняем клик


    //все вьюхи на блюпринте
    private val questionUi = QuestionUi(
        text = question,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    private val choicesUiIdList = listOf(
        R.id.firstChoiceButton,
        R.id.secondChoiceButton,
        R.id.thirdChoiceButton,
        R.id.fourthChoiceButton
    )
    //был лист стрингов , и мы создаём список наших чойзюаев
    private val choicesUiList = choices.mapIndexed { index, text ->
        ChoicesUi(
            choicesUiIdList[index],
            text = text,
            containerIdMatcher = containerIdMatcher,
            containerClassTypeMatcher = classTypeMatcher
        )
    }

    private val checkUi = ButtonUi(
        id = R.id.checkButton,
        textResId = R.string.check,
        colorHex = "#E84DDD",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val nextUi = ButtonUi(
        id = R.id.nextButton,
        textResId = R.string.next,
        colorHex = "#30F8F5",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )


    fun assertAskedQuestionState() {
        //для каждого большого ассерта стейта экрана мы делаем внутренний
        // ассерт стейт всех вложенных объектов
        questionUi.assertTextVisible()
        choicesUiList.forEach {
            it.assertAvailableToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertNotVisible()
    }

    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertNotAvailableToChooseState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertAvailableToChooseState()
        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    //нажать на чек
    fun clickCheck() {
        checkUi.click()

    }

    fun assertAnswerCheckedStateFirstIsCorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvailableToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }


    fun clickSecondChoice() {
        choicesUiList[1].click()

    }

    fun assertSecondChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 1) {
                choiceUi.assertNotAvailableToChooseState()
            } else choiceUi.assertAvailableToChooseState()

        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    fun assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        choicesUiList[1].assertInCorrectState()
        choicesUiList[2].assertNotAvailableToChooseState()
        choicesUiList[3].assertNotAvailableToChooseState()
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickNext() {
        nextUi.click()
    }

}