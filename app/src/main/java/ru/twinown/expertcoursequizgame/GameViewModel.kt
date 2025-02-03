package ru.twinown.expertcoursequizgame

class GameViewModel(private val repository: GameRepository) {

    fun init(): GameUiState {
        val data = repository.questionAndChoices()
        return GameUiState.AskedQuestion(
            data.question,
            data.choices
        )
    }

    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            data.choices.mapIndexed{index,text->
                if(index ==0)
                    ChoiceUiState.NotAvailableToChoose(text)
                else  ChoiceUiState.AvailableToChoose(text)
            }

        )
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            data.choices.mapIndexed{index,text->
                if(index ==1)
                    ChoiceUiState.NotAvailableToChoose(text)
                else  ChoiceUiState.AvailableToChoose(text)
            }
        )
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            data.choices.mapIndexed{index,text->
                if(index ==2)
                    ChoiceUiState.NotAvailableToChoose(text)
                else  ChoiceUiState.AvailableToChoose(text)
            }
        )
    }

    fun chooseFourth(): GameUiState {
        repository.saveUserChoice(3)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            data.choices.mapIndexed{index,text->
                if(index ==3)
                    ChoiceUiState.NotAvailableToChoose(text)
                else  ChoiceUiState.AvailableToChoose(text)
            }
        )
    }

    fun check(): GameUiState {
        val data = repository.questionAndChoices()
        val correctAndUserChoiceIndexes = repository.check()
        return GameUiState.AnswerChecked(
            data.question,
            data.choices.mapIndexed { index, choice ->
                if (correctAndUserChoiceIndexes.correctIndex == index)
                    ChoiceUiState.Correct(text = choice)
                else if (correctAndUserChoiceIndexes.userChoiceIndex == index) {
                    ChoiceUiState.Incorrect(text = choice)
                } else {
                    ChoiceUiState.NotAvailableToChoose(choice)
                }
            }
        )
    }

    fun chooseNext(): GameUiState {
        repository.next()
        return init()
    }
}
