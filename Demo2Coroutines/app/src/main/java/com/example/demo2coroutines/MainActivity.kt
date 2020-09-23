package com.example.demo2coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var stringListAdapter: StringListAdapter
    //private var items: MutableList<LiveData<String>> = mutableListOf()
    private var items: MutableList<String> = mutableListOf()
    private var temoporaryItems: MutableList<String> = mutableListOf()
    private lateinit var dataManager: DataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataManager = DataManager()
//        recyclerViewAdapter = RecyclerViewAdapter(this, items){
//            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
//        }
        stringListAdapter = StringListAdapter(){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = stringListAdapter
            stringListAdapter.submitList(items)

        btnShow.setOnClickListener(View.OnClickListener {
            if(items.isEmpty()) {
                GlobalScope.launch(Main) {
                    for (i in 0 until dataManager.dataList.size) {
                        items.add(dataManager.dataList[i])
                        stringListAdapter.notifyItemInserted(i)
                        recyclerView.scrollToPosition(i)
                        delay(1000)
                    }
                    Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}