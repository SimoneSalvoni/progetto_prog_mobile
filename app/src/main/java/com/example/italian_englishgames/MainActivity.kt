package com.example.italian_englishgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.NavigationUI
import com.example.italian_englishgames.impiccato.ImpActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
       // NavigationUI.setupActionBarWithNavController(this, view.find)
        val buttonimp = findViewById<Button>(R.id.buttonImp)
        buttonimp.setOnClickListener{
            val intent = Intent(this, ImpActivity::class.java)
            startActivity(intent)
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    */
}