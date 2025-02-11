package ru.twinown.expertcoursequizgame

import android.app.Application

class QuizApp:Application() {

    //это и называется мануал диай
    lateinit var   viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = GameViewModel(GameRepository.Base())
    }
}