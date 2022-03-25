package com.example.chattingapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login_form_2.*
import model.User

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_form_2)

        init()
        handleEvent()

    }

    fun init() {
        auth = FirebaseAuth.getInstance()
    }

    fun handleEvent() {
        tv_signup.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var intent: Intent = Intent(applicationContext, SignUpActivity::class.java)
                startActivity(intent)
            }

        })

        btn_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                SignIn()
            }

        })
    }

    fun SignIn() {

        var email: String = edt_email.text.toString()
        var password: String = edt_password.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        var intent: Intent = Intent(this, UserListActivity::class.java)
                        var bundle: Bundle = Bundle()
                        bundle.putString("key_uid", auth.currentUser?.uid)
                        intent.putExtras(bundle)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            applicationContext,
                            "Email or password is incorrect!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        }
    }
}