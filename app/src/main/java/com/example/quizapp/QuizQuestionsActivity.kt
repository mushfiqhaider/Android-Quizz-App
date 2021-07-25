package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.widget.Toast.makeText

class QuizQuestionsActivity : AppCompatActivity(),View.OnClickListener {

    private var mCurrentPosition:Int= 1
    private var mquestionslist:ArrayList<Questions>?=null
    private var mSelectedOptionPosition:Int = 0
    private var mCorrectAnswers:Int =0
    private var mUserName:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        mUserName=intent.getStringExtra(Constants.USER_NAME)

        mquestionslist = Constants.getQuestions()
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        SetQuestion()

        Option_one.setOnClickListener(this)
        Option_two.setOnClickListener(this)
        Option_three.setOnClickListener(this)
        Option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }
    private fun SetQuestion(){
        val question = mquestionslist!![mCurrentPosition-1]
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        defaultOptionsView()
        if(mCurrentPosition == mquestionslist!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = "SUBMIT"
        }

        progressbar.progress = mCurrentPosition
        progressbartext.text = "$mCurrentPosition"+"/"+progressbar.max
        tv_question.text=question!!.question
        Option_one.text=question.OptionOne
        Option_two.text=question.Optiontwo
        Option_three.text=question.OptionThree
        Option_four.text=question.OptionFour

    }
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        options.add(0,Option_one)
        options.add(1,Option_two)
        options.add(2,Option_three)
        options.add(3,Option_four)

        for (option in options){
            val grey = "#7A8089"
            val greyInt: Int = Color.parseColor(grey)
            option.setTextColor(greyInt)
            option.typeface= Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        when(v?.id){
            R.id.Option_one ->{
                SelectedOptionView(Option_one,1)
            }
            R.id.Option_two -> {
                SelectedOptionView(Option_two, 2)
            }
            R.id.Option_three -> {
                SelectedOptionView(Option_three, 3)
            }
            R.id.Option_four -> {
                SelectedOptionView(Option_four, 4)
            }
            R.id.btn_submit -> {
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++
                    when {
                        mCurrentPosition <= mquestionslist!!.size -> {
                            SetQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECTANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mquestionslist!!.size)
                            startActivity(intent)
                        }
                    }
                }else {
                    val question = mquestionslist?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        AnswerView(mSelectedOptionPosition, R.drawable.wrong_option_border)
                    }else{
                        mCorrectAnswers++
                    }
                    AnswerView(question.correctAnswer, R.drawable.correct_option_border)
                    if (mCurrentPosition == mquestionslist!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "Go to next question"
                    }
                    mSelectedOptionPosition = 0
                }

            }

        }
    }

    private fun AnswerView(answer:Int,drawableview:Int){
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)
        when(answer) {
            1 -> {
                Option_one.background = ContextCompat.getDrawable(this, drawableview)

            }
            2 -> {
                Option_two.background = ContextCompat.getDrawable(this, drawableview)
            }
            3 -> {
                Option_three.background = ContextCompat.getDrawable(this, drawableview)
            }
            4 -> {
                Option_four.background = ContextCompat.getDrawable(this, drawableview)

            }
        }
    }

    private fun SelectedOptionView(tv:TextView,SelectedOptionNum:Int){
        defaultOptionsView()
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)
        val progressbartext = findViewById<TextView>(R.id.progressbartext)
        val tv_question = findViewById<TextView>(R.id.question)
        val Option_one = findViewById<TextView>(R.id.Option_one)
        val Option_two = findViewById<TextView>(R.id.Option_two)
        val Option_three = findViewById<TextView>(R.id.Option_three)
        val Option_four = findViewById<TextView>(R.id.Option_four)
        val btn_submit = findViewById<Button>(R.id.btn_submit)

        mSelectedOptionPosition = SelectedOptionNum
        val grey = "#363A43"
        val greyInt: Int = Color.parseColor(grey)
        tv.setTextColor(greyInt)
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,R.drawable.selected_option_border)

    }
}


