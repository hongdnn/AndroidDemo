package com.example.firebasedemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val timeOut = 60
    private var mCallback: OnVerificationStateChangedCallbacks? = null

    private fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))

    private fun Context.hideKeyboard(view: View) =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                ).hideSoftInputFromWindow(view.windowToken, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFirebaseLogin()

        btnRequest.setOnClickListener {
            //TODO send OTP to the selected phone number
            if (edtPhone.text != null && edtPhone.text.toString().trim() != "") {
                mCallback?.let { callback ->
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+84${edtPhone.text.toString()}",                     // Phone number to verify
                        timeOut.toLong(),                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        this@MainActivity,        // Activity (for callback binding)
                        callback
                    )
                }
                startActivity(Intent(this, VerifyOTPActivity::class.java))
            } else {
                hideKeyboard()
                Toast
                    .makeText(
                        this@MainActivity,
                        "Please type phone number",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }

        }

    }


    private fun startFirebaseLogin() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()
//
//        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        mCallback = object : OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                Toast.makeText(this@MainActivity, "verification completed", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@MainActivity, "verification failed", Toast.LENGTH_SHORT).show()
                Log.d("FirebaseException", e.toString())
            }

            override fun onCodeSent(
                verificationId: String,
                forceResendingToken: ForceResendingToken
            ) {
                super.onCodeSent(verificationId, forceResendingToken)
                Log.d("verificationCode", verificationId)
                val sharedPreferences = getSharedPreferences("OTP",Context.MODE_PRIVATE)
                with(sharedPreferences.edit()){
                    putString("verificationCode",verificationId)
                    apply()
                }
                Toast.makeText(this@MainActivity, "Code Sent", Toast.LENGTH_SHORT).show()

            }
        }
    }

}
