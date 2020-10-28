package com.example.firebasedemo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private val timeOut = 60


    private lateinit var auth: FirebaseAuth
    var job: Deferred<Unit>? = null

    private var mCallback: OnVerificationStateChangedCallbacks? = null
    var verificationCode: String = ""

    private fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))

    private fun Context.hideKeyboard(view: View) =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                ).hideSoftInputFromWindow(view.windowToken, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFirebaseLogin()

        //setPhoneNumber()

        btnRequest.setOnClickListener {
            //TODO send OTP to the selected phone number
            if (edtPhone.text != null && edtPhone.text.toString().trim() != "")
                mCallback?.let { callback ->
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        edtPhone.text.toString(),                     // Phone number to verify
                        timeOut.toLong(),                           // Timeout duration
                        TimeUnit.SECONDS,                // Unit of timeout
                        this@MainActivity,        // Activity (for callback binding)
                        callback
                    )
                }
            else {
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
        btnVerify.setOnClickListener {
            hideKeyboard()
            if (edtOTP.text.toString().trim() != "") {
                val credential =
                    PhoneAuthProvider.getCredential(verificationCode, edtOTP.text.toString())
                signInWithPhone(credential)
            } else {
                Toast
                    .makeText(
                        this@MainActivity,
                        "Please type OTP number",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }

        logout.setOnClickListener {
            if (job != null && job!!.isActive)
                job!!.cancel()
            FirebaseAuth.getInstance().signOut()
            setPhoneNumber()
        }
    }


    private fun countDown() = GlobalScope.async(Dispatchers.IO) {
        hideKeyboard()

        repeat(timeOut + 1) {
            val res = DecimalFormat("00").format(timeOut - it)
            withContext(Dispatchers.Main) {
                tvCountDown.text = "00:$res"
            }
            delay(1000)
        }
        println("finished")

    }

    private fun signInWithPhone(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Correct OTP", Toast.LENGTH_SHORT)
                        .show()
                    if (job!!.isActive)
                        job!!.cancel()
                    setPhoneNumber()
                } else {
                    Toast.makeText(this@MainActivity, "Incorrect OTP", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun setPhoneNumber() {
        val user = auth.currentUser
        try {
            tvUserPhone.text = user?.phoneNumber
        } catch (e: Exception) {
            Toast.makeText(this, "Phone number not found", Toast.LENGTH_SHORT).show()
            tvUserPhone.text = "---"
        }
    }

    private fun startFirebaseLogin() {
        auth = FirebaseAuth.getInstance()
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
                s: String,
                forceResendingToken: ForceResendingToken
            ) {
                super.onCodeSent(s, forceResendingToken)
                verificationCode = s
                Log.d("verificationCode", verificationCode)
                Toast.makeText(this@MainActivity, "Code Sent", Toast.LENGTH_SHORT).show()
                job = if (job == null || job!!.isCancelled)
                    countDown()
                else {
                    job!!.cancel()
                    countDown()
                }

            }
        }
    }
}
