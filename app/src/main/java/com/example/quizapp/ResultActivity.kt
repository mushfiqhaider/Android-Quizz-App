package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val name = findViewById<TextView>(R.id.Name)
        val score = findViewById<TextView>(R.id.score)
        val btn_finish = findViewById<Button>(R.id.btn_finish)

        val username=intent.getStringExtra(Constants.USER_NAME)
        name.text = username
        val TotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val CorrectAnswers = intent.getIntExtra(Constants.CORRECTANSWERS,0)

        score.text="Your score is $CorrectAnswers out of $TotalQuestions"
        btn_finish.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}