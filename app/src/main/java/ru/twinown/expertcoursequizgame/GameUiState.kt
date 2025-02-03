package ru.twinown.expertcoursequizgame

import ru.twinown.expertcoursequizgame.databinding.ActivityMainBinding

interface GameUiState {
    fun update(binding: ActivityMainBinding) :Unit= throw IllegalStateException("")

    //TODO ПОЧЕМУ ДАТА КЛАССЫ ?????????????????????ответ ниже
    //потому что у нас будет проверяться текст, а потлму в дассаъ должны быть переопределены
    //иквалс  и хэшкод-> дата класс,поэтому
  data  class AskedQuestion(val question: String,
                           val choices: List<String>) :
        GameUiState {

    }

  data  class ChoiceMade(val question: String,
                         val choices: List<ChoiceUiState>) :
        GameUiState {

    }

   data class AnswerChecked(val question: String,
                            val choices: List<ChoiceUiState>) :
        GameUiState {

    }
}
interface ChoiceUiState{

  data  class NotAvailableToChoose(val text: String) : ChoiceUiState {

    }

  data  class AvailableToChoose(val text: String) : ChoiceUiState {

    }

   data class Correct(val text: String) : ChoiceUiState {

    }

   data class Incorrect(val text: String) : ChoiceUiState {

    }

}