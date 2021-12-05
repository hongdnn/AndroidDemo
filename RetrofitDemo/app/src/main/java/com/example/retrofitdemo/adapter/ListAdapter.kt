package com.example.retrofitdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.entity.Question
import kotlinx.android.synthetic.main.question_item.view.*

import com.example.testlib.MyClass

class ListAdapter(
    private val mQuestions: List<Question>
) : RecyclerView.Adapter<ListAdapter.QuestionViewHolder>() {

    lateinit var test: MyClass

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(mQuestions[position], position)
    }

    override fun getItemCount(): Int {
        return mQuestions.size
    }

    inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question, position: Int) {
            itemView.positionNumber.text =
                itemView.context.getString(R.string.question_num, position + 1)
            itemView.title.text = itemView.context.getString(R.string.ques_title, question.title)
            itemView.link.text = itemView.context.getString(R.string.ques_link, question.link)
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Clicked on: " + itemView.title.text,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
