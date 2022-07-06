package com.example.idcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.mindorks.firebaseloginsignup.Outctivity

class Home: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var testing=findViewById<LinearLayout>(R.id.testing);
        testing.setOnClickListener {
            var intent=Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
        var therap=findViewById<LinearLayout>(R.id.therap);
        therap.setOnClickListener {
            var intent=Intent(this, TherapistActivity::class.java)
            startActivity(intent)
        }
        var safety=findViewById<LinearLayout>(R.id.safety);
        safety.setOnClickListener {
            var intent=Intent(this, SafetyActivity::class.java)
            startActivity(intent)
        }
        var act=findViewById<LinearLayout>(R.id.act);
        act.setOnClickListener {
            var intent=Intent(this, ActActivity::class.java)
            startActivity(intent)
        }

    }
}