package ru.twinown.expertcoursequizgame.game

import android.view.View

import androidx.appcompat.widget.AppCompatButton
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import ru.twinown.expertcoursequizgame.ButtonColorMatcher


class ChoicesUi(
    id: Int,
    text: String,
    containerIdMatcher: Matcher<View>,
    containerClassTypeMatcher: Matcher<View>
) : AbstractButton(
    onView(
        allOf(
            containerIdMatcher,
            containerClassTypeMatcher,
            withId(id),
            withText(text),
            isAssignableFrom(AppCompatButton::class.java),
            isDisplayed()
        )
    )
) {

    fun assertAvailableToChooseState() {
        interaction.check(matches(ButtonColorMatcher("#4D90E8")))
            .check(matches(isEnabled()))
            .check(matches(isClickable()))
    }


    fun assertNotAvailableToChooseState() {
        interaction.check(matches(isNotEnabled()))

    }

    fun assertCorrectState() {
        interaction.check(matches(ButtonColorMatcher("#66FF3C")))
            .check(matches(isEnabled()))
            .check(matches(isNotClickable()))
    }

    fun assertInCorrectState() {
        interaction.check(matches(ButtonColorMatcher("#FF0D11")))
            .check(matches(isEnabled()))
            .check(matches(isNotClickable()))
    }

}
