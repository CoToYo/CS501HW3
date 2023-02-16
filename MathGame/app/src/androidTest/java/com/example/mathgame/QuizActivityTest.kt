package com.example.mathgame

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class QuizActivityTest {

    private lateinit var scenario: ActivityScenario<QuizActivity>

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun checkSubmitButton() {
        Espresso.onView(ViewMatchers.withId(R.id.submit))
            .check(ViewAssertions.matches(ViewMatchers.withText("SUBMIT")))
    }

    @Test
    fun checkQuestionAppear() {
        Espresso.onView(ViewMatchers.withId(R.id.question))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}