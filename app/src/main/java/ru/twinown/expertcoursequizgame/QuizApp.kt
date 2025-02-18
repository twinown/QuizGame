package ru.twinown.expertcoursequizgame

import android.app.Application
import android.content.Context

class QuizApp:Application() {

    //создание шердов ты начал с инткэша. во время создания, но при запуске проги, естсессна,запускается
    //сначала здесь
    //это и называется мануал диай
    lateinit var   viewModel: GameViewModel

    override fun onCreate() {
        super.onCreate()
        //device explorer,чтоб найти этот файл/data/data/quizgane/sharedpref
        val sharedPreferences = getSharedPreferences("QuizAppData",Context.MODE_PRIVATE)
        viewModel = GameViewModel(GameRepository.Base(
            IntCache.Base(sharedPreferences,"indexKey",0),
            IntCache.Base(sharedPreferences,"userChoiceIndexKey",-1)
        ))
    }
}