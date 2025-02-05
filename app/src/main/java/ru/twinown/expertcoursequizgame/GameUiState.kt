package ru.twinown.expertcoursequizgame

import android.view.View
import ru.twinown.expertcoursequizgame.databinding.ActivityMainBinding

interface GameUiState {
    fun update(binding: ActivityMainBinding)

    //свойства из блюпринта!!!
    //тк у нас для каждого экрана имеются состояния всех семи элементов
    //то мы иъ пишем все в одном абстрактном классе, а не в каждом наследнике =
    //не нарушаем DRY
    abstract class Abstract(
        private val questionText: String,
        private val choicesStateList: List<ChoiceUiState>,
        private val checkVisibility: Int,
        private val nextVisibility: Int
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) = with((binding)) {
            questionTextView.text = questionText
            choicesStateList[0].update(firstChoiceButton)
            choicesStateList[1].update(secondChoiceButton)
            choicesStateList[2].update(thirdChoiceButton)
            choicesStateList[3].update(fourthChoiceButton)
            checkButton.visibility = checkVisibility
            nextButton.visibility = nextVisibility
        }
    }

    //TODO ПОЧЕМУ ДАТА КЛАССЫ ?????????????????????ответ ниже
    //потому что у нас будет проверяться текст, а потлму в дассаъ должны быть переопределены
    //иквалс  и хэшкод-> дата класс,поэтому
    //это начальное состояние экрана типо
    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : Abstract(
        question,
        choices.map { ChoiceUiState.AvailableToChoose(it) },
        checkVisibility = View.INVISIBLE,
        nextVisibility = View.INVISIBLE
    )

    //todo nпочему здесь не сделали выбранному NotAvailableToChoose
    //сделали, но во вьюмодельке
    data class ChoiceMade(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) : Abstract(
        question,
        //почему тут уже по-другому ?
        choices,
        checkVisibility = View.VISIBLE,
        nextVisibility = View.INVISIBLE
    )

    data class AnswerChecked(
        private val question: String,
        private val choices: List<ChoiceUiState>
    ) :
        Abstract(
            question,
            choices,
            checkVisibility = View.INVISIBLE,
            nextVisibility = View.VISIBLE
        )
}

