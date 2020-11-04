package com.example.firebasedemo


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

const val RC_SIGN_IN = 123

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val timeOut = 60
    private var phone = "+"
    private fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))

    private fun Context.hideKeyboard(view: View) =
        (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                ).hideSoftInputFromWindow(view.windowToken, 0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseLogin.startFirebaseLogin(this)

        signInButton.setSize(SignInButton.SIZE_WIDE)
        signInButton.setOnClickListener {
            onClick(signInButton)
        }

        btnRequest.setOnClickListener {
            onClick(btnRequest)
        }
    }

    override fun onClick(v: View) {
        when (v) {
            signInButton -> signInByGoogle()
            btnRequest -> signInByPhone()
        }
    }

    private fun signInByPhone() {
        if (edtPhone.text != null && edtPhone.text.toString().trim() != "") {
            FirebaseLogin.mCallback?.let { callback ->
                phone += cppCode.selectedCountryCode + edtPhone.text
                val options = PhoneAuthOptions.newBuilder(FirebaseLogin.auth)
                    .setPhoneNumber(phone)
                    .setTimeout(timeOut.toLong(),TimeUnit.SECONDS)
                    .setActivity(this)
                    .setCallbacks(callback)
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
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

    private fun signInByGoogle() {
        val signInIntent: Intent = FirebaseLogin.mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Log.d("AuthWithGoogle", "firebaseAuthWithGoogle:" + account?.id)
                account?.idToken?.let {
                    FirebaseLogin.firebaseAuthWithGoogle(this@MainActivity, it)
                    FirebaseLogin.checkLogin.observe(this@MainActivity, { check ->
                        if (check) {
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }
                    })
                }
            } catch (e: ApiException) {
                Log.w("SignInFailed", "Google sign in failed", e)
                Snackbar.make(activity_main, "Sign in Failed.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


}
