package ru.twinown.expertcoursequizgame

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton

//это уже одтельное состояние кнопок выбора
interface ChoiceUiState {

    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean,
        private val enabled: Boolean
    ) : ChoiceUiState {

        override fun update(button: AppCompatButton) = with(button) {
            text = value
            if (enabled)
                setBackgroundColor(Color.parseColor(color))
            isEnabled = enabled
            isClickable = clickable
        }
    }

    data class NotAvailableToChoose(private val text: String) : Abstract(
        text, "", false, false
    )

    data class AvailableToChoose(private val text: String) : Abstract(
        text, "#4D90E8", true, true
    )

    data class Correct(private val text: String) : Abstract(
        text, "#66FF3C", false, true
    )

    data class Incorrect(private val text: String) : Abstract(
        text, "#FF0D11", false, true
    )

}