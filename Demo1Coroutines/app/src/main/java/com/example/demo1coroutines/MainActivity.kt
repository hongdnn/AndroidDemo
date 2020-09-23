package com.example.demo1coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private var result: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnResult.setOnClickListener(View.OnClickListener {
            GlobalScope.launch(Main) {
                // application context, basecontext, context
                try {
                    result = edtNum1.text.toString().toDouble() + edtNum2.text.toString().toDouble()
                    delay(3000)
                    //withContext(Dispatchers.Main){
                    Toast.makeText(
                        this@MainActivity,
                        "Result: ${result * result}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // }
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        applicationContext,
                        "Number format exception",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}