package com.example.firebasedemo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseLogin : Application() {

    companion object {
         var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
         lateinit var mGoogleSignInClient: GoogleSignInClient
          lateinit var auth: FirebaseAuth
         var checkLogin = MutableLiveData<Boolean>()

         fun startFirebaseLogin(activity: Activity) {
             checkLogin.value = false
            auth = Firebase.auth
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.applicationContext.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
        }

         fun firebaseAuthWithGoogle(activity: Activity,idToken: String) {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    checkLogin.value = task.isSuccessful
                }
        }

        fun handleFacebookAccessToken(activity: Activity,token: AccessToken) {

            val credential = FacebookAuthProvider.getCredential(token.token)
            auth.signInWithCredential(credential)
                .addOnCompleteListener(activity) { task ->
                    checkLogin.value = task.isSuccessful
                }
        }

        fun startRequestOTP(activity: Activity){
            mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    println("completed")
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    println("failed")
                }

                override fun onCodeSent(
                    verificationId: String,
                    forceResendingToken: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, forceResendingToken)
                    Log.d("verificationCode", verificationId)

                    val sharedPreferences = activity.getSharedPreferences("OTP", Context.MODE_PRIVATE)
                    with(sharedPreferences.edit()){
                        putString("verificationCode", verificationId)
                        apply()
                    }
                }
            }
        }
    }

}