package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.mathgame.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{view: View ->

            if(binding.username.text.toString().equals("User") and
                binding.password.text.toString().equals("Pass")){
                val intent = Intent(this, SelectOperator::class.java)
                startActivity(intent)
            }
            else{
                binding.invalidLogin.visibility=View.VISIBLE
            }
        }
    }
}