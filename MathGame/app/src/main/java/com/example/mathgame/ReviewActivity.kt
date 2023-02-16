package com.example.mathgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathgame.databinding.ActivityQuizBinding
import com.example.mathgame.databinding.ActivityReviewBinding
import java.util.stream.IntStream.range


class ReviewActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var binding: ActivityReviewBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var questionsList: ArrayList<String>
    private lateinit var questionsAdaptor: QuestionsAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView=findViewById(R.id.list)

        questionsList= intent.getStringArrayListExtra(EXTRA_TEXT_LIST) as ArrayList<String>

        questionsAdaptor=QuestionsAdaptor(this,questionsList)
        Log.d("TAG",questionsList.toString())

        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=questionsAdaptor

        binding.reTake.setOnClickListener{view: View ->
            finish()
        }

    }
    private fun addView(){
        val inflater=LayoutInflater.from(this)
        val view=inflater.inflate(R.layout.questions_list,null)
        val question=view.findViewById<TextView>(R.id.question)
    }
}