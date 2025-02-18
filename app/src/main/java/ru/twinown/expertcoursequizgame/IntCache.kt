package ru.twinown.expertcoursequizgame

import android.content.SharedPreferences

//класс обёртка
interface IntCache {
    fun save(newValue:Int)
    fun read():Int

    class Base(
        private val sharedPreferences:SharedPreferences,
        private val key:String,
        private val defaultValue :Int
        ): IntCache {

        override fun save(newValue:Int) {
            sharedPreferences.edit().putInt(key,newValue).apply()//commmit -типо на другом треде
        }

        override fun read():Int {
        return sharedPreferences.getInt(key,defaultValue)
        }
    }
}