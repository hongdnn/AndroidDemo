package com.example.retrofitdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.R
import com.example.retrofitdemo.adapter.ListAdapter
import com.example.retrofitdemo.entity.Question
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val questionViewModel: QuestionViewModel by viewModel()
    private lateinit var mAdapter: ListAdapter
    private var mQuestions: MutableList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView(mQuestions)
        fetchQuetionList()
    }

    private fun fetchQuetionList() {
        questionViewModel.fetchQuestion("android")
        questionViewModel.questions.observe(this, {
            println("Size: ${it.size}")
            it.let { mQuestions.addAll(it) }
            //setupRecyclerView(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView(questionlist: List<Question>){
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ListAdapter(questionlist)
        listRecyclerView.adapter = mAdapter
    }

}
