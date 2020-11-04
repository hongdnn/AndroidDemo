package com.example.firebasedemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verify_otp.*

class VerifyOTPActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))

    private fun Context.hideKeyboard(view: View) =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                ).hideSoftInputFromWindow(view.windowToken, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_otp)

        btnVerify.setOnClickListener {
            hideKeyboard()
            val sharedPref = getSharedPreferences("OTP",Context.MODE_PRIVATE)
            val verificationCode = sharedPref.getString("verificationCode", null)
            println("Ve: $verificationCode")
            if (verificationCode != null) {
                if (edtOTP.text.toString().trim() != "") {
                    val credential =
                        PhoneAuthProvider.getCredential(verificationCode, edtOTP.text.toString())
                    signInWithOTP(credential)
                } else {
                    Toast
                        .makeText(
                            this,
                            "Please type OTP number",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
            } else {
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithOTP(credential: PhoneAuthCredential) {
        auth = FirebaseAuth.getInstance()
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this@VerifyOTPActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}