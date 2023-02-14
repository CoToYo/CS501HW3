package com.example.mathgame

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mathgame.databinding.ActivityQuizBinding
import com.google.android.material.snackbar.Snackbar


private const val TAG = "QuizActivity"

public const val EXTRA_TEXT_LIST="com.example.mathgame.EXTRA_TEXT_LIST"

private lateinit var builder: AlertDialog.Builder

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding

    private var operator=""

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        operator=intent.getStringExtra(EXTRA_TEXT).toString()
        quizViewModel.operationChosen = intent.getStringExtra("OPERATOR").toString()

        builder=AlertDialog.Builder(this)

        //checking the last question incase of rotation
        if(quizViewModel.getQuestionSize==0){
            generateQuestion();
        }
        else{
            existingQuestion();
        }

        //checking if the answer is empty
        binding.answer.addTextChangedListener(object : TextWatcher {

            @Override
            override fun afterTextChanged(s:Editable) {}

            @Override
            override fun beforeTextChanged(s:CharSequence, start:Int,
                                           count:Int, after:Int) {
            }

            @Override
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(binding.answer.text.isNotBlank()){
                    binding.submit.isEnabled=true
                }
                else{
                    binding.submit.isEnabled=false
                }
            }
        })

        binding.submit.setOnClickListener{view: View->
            checkAnswer();
            nextQuestion();
        }

        binding.reStart.setOnClickListener{view:View->
            finish()
        }
    }
    private fun existingQuestion(){
        binding.question.text=quizViewModel.getCurrentQuestion
    }
    private fun generateQuestion(){
        val number1=(1..100).random()
        val number2=(1..20).random()

        val opt=if(operator.equals("Addition")){"+"}
        else{"-"}

        quizViewModel.setNumbers(number1.toString(), number2.toString())
        quizViewModel.addQuestion(number1.toString()+" "+opt+" "+number2.toString())
        existingQuestion()
    }
    private fun checkAnswer(){
        quizViewModel.setAnswered(binding.answer.text.toString().toInt())

        Log.i("Check Answer", "Checking answer here" + quizViewModel.operationChosen)
        var snack:Snackbar;
        if(quizViewModel.operationChosen.equals("+")){
            val answer = Integer.parseInt(quizViewModel.firstNum) + Integer.parseInt(quizViewModel.secondNum)
            if(Integer.parseInt(binding.answer.text.toString()) == answer){
                snack=Snackbar.make(binding.root, "Correct Answer", Snackbar.LENGTH_SHORT);
                quizViewModel.updateScore()
                Log.i("Check Answer", "Correct Answer")
            }else{
                snack=Snackbar.make(binding.root, "Incorrect Answer", Snackbar.LENGTH_LONG);
                Log.i("Check Answer", "It seems wrong")
            }
        }else{
            val answer = Integer.parseInt(quizViewModel.firstNum) - Integer.parseInt(quizViewModel.secondNum)
            if(Integer.parseInt(binding.answer.text.toString()) == answer){
                quizViewModel.updateScore()
                snack=Snackbar.make(binding.root, "Correct Answer", Snackbar.LENGTH_SHORT);
            }else{
                snack=Snackbar.make(binding.root, "Incorrect Answer", Snackbar.LENGTH_LONG);
            }
        }

        val view: View = snack.getView()
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snack.show()

    }
    private fun nextQuestion(){
        if(quizViewModel.getQuestionSize==10){
            //pop up
            builder.setTitle("Quiz over! You Scored ${quizViewModel.score}!")
                .setMessage("Do you want to check your answers?")
                .setCancelable(true)
                .setPositiveButton("Yes"){dialogInterface ,it->
                    Log.d("TAG",quizViewModel.getListOfQuestion().toString())
                    val intent = Intent(this, ReviewActivity::class.java)
                    intent.putExtra(EXTRA_TEXT_LIST,quizViewModel.getListOfQuestion())
                    startActivity(intent)
                }
                .setNegativeButton("No"){dialogInterface ,it->
                    dialogInterface.cancel()
                }
                .setNeutralButton("re-take quiz"){dialogInterface ,it->
                    finish()
                }
                .show()
        }
        else{
            binding.answer.text=null;
            binding.submit.isEnabled=false;
            generateQuestion()
        }
    }
}