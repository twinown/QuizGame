package ru.twinown.expertcoursequizgame

//на момен первого теста тебе не надо писать базовый рпозиторий  достаточно интерфейса
interface GameRepository {
    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

    class Base(
            private val list :List<QuestionAndChoices> = listOf(
            QuestionAndChoices(question = "What color is the sky?",
                choices = listOf("blue","green","red","yellow"),
                correctIndex=0),
            QuestionAndChoices(question = "What color is the grass?",
                choices = listOf("green","blue","yellow","red"),
                correctIndex = 0)
        )
    ):GameRepository{

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
            if(index +1 ==list.size)
                index = 0
            else index++

        }
    }
}
