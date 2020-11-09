package com.example.firebasedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tvName.text = FirebaseAuth.getInstance().currentUser?.displayName

        logout.setOnClickListener {
            FirebaseLogin.auth.currentUser?.let { user ->
                for (userInfo in user.providerData) {
                    if (userInfo.providerId == "facebook.com") {
                        LoginManager.getInstance().logOut()
                    }
                }
            }
            FirebaseLogin.auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
