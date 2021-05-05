package com.example.italian_englishgames.impiccato

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.italian_englishgames.R

class ImpMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imp_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        private lateinit var GameModel: ImpViewModel

        Log.i("GameFragment", "Called ViewModelProvider.get")
        GameModel = ViewModelProvider(this).get(ImpViewModel::class.java)

        findViewById<TextView>(R.id.displayedText).text = GameModel.shownword.get()
        findViewById<TextView>(R.id.wrongChoice).text = GameModel.chosenLetters.get()
        findViewById<Button>(R.id.guessButton).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }
}