package com.example.italian_englishgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val buttonimp = findViewById<Button>(R.id.buttonimp)
        buttonimp.setOnClickListener{
            val intent = Intent(this, ImpActivity::class.java)
            startActivity(intent)
        }
    }
}