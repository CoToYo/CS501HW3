package com.example.mathgame

import androidx.lifecycle.SavedStateHandle
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.runner.RunWith

public class QuizActivityTest {

    val savedStateHandle = SavedStateHandle()
    val quizViewModel = QuizViewModel(savedStateHandle)

    @Test
    fun checkNumberEntry() {
        quizViewModel.setNumbers("1", "2")
        assertEquals(quizViewModel.firstNum, "1")
        assertEquals(quizViewModel.secondNum, "2")
    }

    @Test
    fun checkQuestionEntry() {
        quizViewModel.addQuestion("1 + 2")
        assertEquals(quizViewModel.getListOfQuestion().size, 1)
    }

    @Test
    fun checkOperatorEntry() {
        quizViewModel.setoperator("+")
        assertEquals(quizViewModel.getOperator, "+")
    }
}