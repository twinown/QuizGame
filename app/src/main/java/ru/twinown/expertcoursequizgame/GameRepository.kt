package ru.twinown.expertcoursequizgame

//на момен первого теста тебе не надо писать базовый рпозиторий  достаточно интерфейса
interface GameRepository {
    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
}
