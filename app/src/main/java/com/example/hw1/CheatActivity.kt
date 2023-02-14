package com.example.hw1

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hw1.databinding.ActivityCheatBinding

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE =
    "com.bignerdranch.android.geoquiz.answer_is_true"

//
private const val QUESTION_SHOWN = "answer_shown"


class CheatActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityCheatBinding

    private var answerIsTrue = false

    //
    private var cheatedFlag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(quizViewModel.storeHasCheated){
            val answerText = when {
                quizViewModel.answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
        }

        //
        if (savedInstanceState != null) {
            cheatedFlag = savedInstanceState.getBoolean(QUESTION_SHOWN)
            setAnswerShownResult(quizViewModel.storeHasCheated)
        }

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            quizViewModel.answerIsTrue = answerIsTrue
            quizViewModel.storeHasCheated = true
            setAnswerShownResult(quizViewModel.storeHasCheated)
        }
    }

    //

    public override fun onSaveInstanceState(savedInstanceState: Bundle){
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean(QUESTION_SHOWN, cheatedFlag)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}