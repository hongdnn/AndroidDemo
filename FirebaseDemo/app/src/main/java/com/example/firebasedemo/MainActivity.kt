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
import com.facebook.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import com.facebook.login.LoginResult

const val RC_SIGN_IN = 123

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private fun Activity.hideKeyboard() = hideKeyboard(currentFocus ?: View(this))
    private lateinit var callbackManager: CallbackManager

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

        btnFBLogin.setOnClickListener {
            onClick(btnFBLogin)
        }

        signInByFacebook()
    }

    override fun onClick(v: View) {
        when (v) {
            signInButton -> signInByGoogle()
            btnRequest -> signInByPhone()
        }
    }

    private fun signInByPhone() {
        if (edtPhone.text != null && edtPhone.text.toString().trim() != "") {
            val intent = Intent(this, VerifyOTPActivity::class.java)
            intent.putExtra("phoneNum",edtPhone.text.toString())
            intent.putExtra("phoneCode",cppCode.selectedCountryCode)
            startActivity(intent)
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

        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                account?.idToken?.let {

                    FirebaseLogin.firebaseAuthWithGoogle(this@MainActivity, it)
                }
            } catch (e: ApiException) {
                layoutProgressBar.visibility = View.GONE
                Snackbar.make(activity_main, "Sign in Failed.", Snackbar.LENGTH_SHORT).show()
            }
        }

        FirebaseLogin.checkLogin.observe(this@MainActivity, { check ->
            if (check) {
                layoutProgressBar.visibility = View.VISIBLE
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        })
    }

    private fun signInByFacebook() {
        callbackManager = CallbackManager.Factory.create()

        btnFBLogin.setReadPermissions("email", "public_profile")
        btnFBLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                FirebaseLogin.handleFacebookAccessToken(this@MainActivity, loginResult.accessToken)
                layoutProgressBar.visibility = View.VISIBLE
            }

            override fun onCancel() {
                Log.d("Login failed", "facebook:onCancel")
                layoutProgressBar.visibility = View.GONE
            }

            override fun onError(error: FacebookException) {
                Log.d("Login exception", "facebook:onError", error)
                layoutProgressBar.visibility = View.GONE
            }
        })
    }

}
