package com.example.mathgame

import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel()  {
    private var operator="";
    private var questionBank = mutableListOf<Question>();

//    Scoring mech Data

    var score = 0

    var operationChosen = ""

    var firstNum = ""

    var secondNum = ""

    fun addQuestion(question:String){
        val questionArr=question.split(" ")
        val ans=if(questionArr[1].equals("+")){questionArr[0].toInt()+questionArr[2].toInt()}
        else{questionArr[0].toInt()-questionArr[2].toInt()}
        questionBank.add(Question(question,ans,null))
    }

    fun setoperator(opr:String){
        operator=opr
    }

    fun setAnswered(answer:Int){
        questionBank[questionBank.size-1].answered=answer
    }

    val getOperator:String
        get() = operator.toString()

    val getCurrentQuestion:String
        get() = questionBank[questionBank.size-1].question

    val getCurrencyAnswer:Number
        get()=questionBank[questionBank.size-1].answer

    val getQuestionSize:Number
        get()=questionBank.size

    fun getListOfQuestion(): ArrayList<String> {
        var arr=ArrayList<String>()
        for (i in 0..questionBank.size-1){
            arr.add(questionBank[i].question+" = "+questionBank[i].answer+";"+questionBank[i].answered)
        }
        return arr
    }

//    Scoring activity vals

    fun chooseOperation(operation:String){
        if(operation.equals("add")){
            operationChosen = "+";
        }else{
            operationChosen = "-";
        }
    }

    fun setNumbers(firstNum: String, secondNum: String){
        this.firstNum = firstNum
        this.secondNum = secondNum
    }

    fun updateScore(){
        score +=1;
    }



}