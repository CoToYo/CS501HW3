package com.example.mathgame

import android.content.Context
import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class QuestionsAdaptor(val context:Context,val questionsList:ArrayList<String>) :RecyclerView.Adapter<QuestionsAdaptor.QuestionViewHolder>(){
    inner class QuestionViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val question=view.findViewById<TextView>(R.id.question)
        val answer=view.findViewById<TextView>(R.id.answer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.questions_list,parent,false)
        return QuestionViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.question.text=questionsList[position].split(";")[0]
        holder.answer.text="Answer: "+questionsList[position].split(";")[1]

        if (questionsList[position].split(";")[0].split(" = ")[1].toInt()==questionsList[position].split(";")[1].toInt()){
            holder.answer.setTextColor( Color.GREEN)
        }
        else{
            holder.answer.setTextColor( Color.RED)
        }
    }
}

