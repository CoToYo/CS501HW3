package com.example.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import com.example.mathgame.databinding.ActivitySelectOperatorBinding

private const val TAG = "SelectOperator"

public const val EXTRA_TEXT="com.example.mathgame.EXTRA_TEXT"

class SelectOperator : AppCompatActivity() {

    private lateinit var binding: ActivitySelectOperatorBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectOperatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(quizViewModel.getOperator!=""){
            binding.start.isEnabled = true;
        }

        binding.start.setOnClickListener{view: View->
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra(EXTRA_TEXT,quizViewModel.getOperator)
            intent.putExtra("OPERATOR", quizViewModel.operationChosen)
            startActivity(intent)
        }

    }


    public fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_addition ->
                    if (checked) {
                        binding.start.isEnabled = true;
                        quizViewModel.setoperator("Addition")
                        quizViewModel.chooseOperation("add")
                    }
                R.id.radio_subtraction ->
                    if (checked) {
                        binding.start.isEnabled = true;
                        quizViewModel.setoperator("Subtraction")
                        quizViewModel.chooseOperation("sub")
                    }
            }
        }
    }
}