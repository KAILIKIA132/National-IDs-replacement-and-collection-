package com.mindorks.firebaseloginsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.idcards.MainActivity
import com.example.idcards.R
import com.google.firebase.auth.FirebaseAuth

class Outctivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var logoutBtn: Button
    private lateinit var updatePass: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }

        setContentView(R.layout.activity_home)

        logoutBtn = findViewById(R.id.logout_btn)
        updatePass = findViewById(R.id.update_pass_btn)

        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//        updatePass.setOnClickListener{
//            val intent = Intent(this, UpdatePassword::class.java)
//            startActivity(intent)
//        }
    }
}