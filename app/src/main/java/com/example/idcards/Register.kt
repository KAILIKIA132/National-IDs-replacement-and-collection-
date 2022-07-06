package com.example.idcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var cpasswordEt: EditText

    private lateinit var signUpBtn: Button
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        emailEt = findViewById(R.id.emailEt)
        passwordEt = findViewById(R.id.passwordEt)
        cpasswordEt = findViewById(R.id.cpasswordEt)

       // loginBtn = findViewById(R.id.login_Btn)
        signUpBtn = findViewById(R.id.signup_Btn)

        signUpBtn.setOnClickListener{
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()
            var cpassword: String = cpasswordEt.text.toString()
            if (password!=cpassword){
                toast("Passwords don't match")
            }

             else if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                toast("Please fill all the fields")

            }
            else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

//        loginBtn.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    fun toast(text:String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}