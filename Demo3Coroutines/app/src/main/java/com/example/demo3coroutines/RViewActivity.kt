package com.example.demo3coroutines

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_r_view.*
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.random.nextInt

class RViewActivity : AppCompatActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var items: ArrayList<Int> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r_view)

        val listSize = intent.getIntExtra("numberOfItem", 0)
        for (i in 0 until listSize) {
            items.add(Random.nextInt(0..100))
        }
        recyclerViewAdapter = RecyclerViewAdapter(this, items)
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@RViewActivity)
            adapter = recyclerViewAdapter
        }


        btnShowBackground.setOnClickListener(View.OnClickListener {
            GlobalScope.launch {
                for (i in 1..10) {
                    println("I: $i")
                    withContext(Dispatchers.Main) {
                        for (y in 0 until listSize) {
                            recyclerView.findViewHolderForAdapterPosition(y)?.let {
                                val itemButton: Button = it.itemView?.findViewById(R.id.btnItem)
                                if ((i % 2 == 0 && itemButton.text.toString()
                                        .toInt() % 2 == 0) || (i % 2 != 0 && itemButton.text.toString()
                                        .toInt() % 2 != 0)
                                ) {
                                    itemButton.setBackgroundColor(Color.BLUE)
                                } else {
                                    itemButton.setBackgroundColor(Color.WHITE)
                                }
                            }
                        }
                    }
                    delay(1000)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        println("activity destroyed")
        cancel()
    }

}