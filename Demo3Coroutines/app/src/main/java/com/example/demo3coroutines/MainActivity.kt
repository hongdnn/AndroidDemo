package com.example.demo3coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener(View.OnClickListener {
            try {
                if(edtNum.text.toString().toInt()>0){
                    val intent = Intent(this, RViewActivity::class.java)
                    intent.putExtra("numberOfItem", edtNum.text.toString().toInt())
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Input greater than 0",Toast.LENGTH_SHORT).show()
                }
            }catch (e: NumberFormatException){
                Toast.makeText(applicationContext,"Number format exception",Toast.LENGTH_SHORT).show()
            }
        })
    }
}