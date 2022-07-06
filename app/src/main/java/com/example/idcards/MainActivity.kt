package com.example.idcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.idcards.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    private lateinit var signupBtn: Button
    private lateinit var loginBtn: Button

    private lateinit var resetPasswordTv: TextView
    private lateinit var signupLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)

        loginBtn = findViewById(R.id.login_Btn)
        //signupBtn = findViewById(R.id.signup_Btn)

        resetPasswordTv = findViewById(R.id.reset_pass_tv)
        signupLink= findViewById(R.id.signup)

        auth = FirebaseAuth.getInstance()

        loginBtn.setOnClickListener {
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {
                        Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

//        signupBtn.setOnClickListener{
//            val intent = Intent(this, Register::class.java)
//            startActivity(intent)
//            finish()
//        }

        resetPasswordTv.setOnClickListener{
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
        signupLink.setOnClickListener {
val intent= Intent(this,Register::class.java)
startActivity(intent)
        }
    }
}