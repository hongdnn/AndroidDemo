package com.example.weeksixproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        btnShow.setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }
    }
}