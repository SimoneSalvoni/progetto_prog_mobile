package com.example.italian_englishgames.impiccato

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.italian_englishgames.R

class ImpGameFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imp_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        lateinit var GameModel: ImpViewModel

        val insertedText: String = findViewById<EditText>(R.id.guessText).getText().toString()
        /*Log.i("GameFragment", "Called ViewModelProvider.get")
        GameModel = ViewModelProvider(this).get(ImpViewModel::class.java)

        findViewById<TextView>(R.id.displayedText).text = GameModel.shownword.get()
        findViewById<TextView>(R.id.wrongChoice).text = GameModel.chosenLetters.get()*/

        findViewById<EditText>(R.id.guessText).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    findViewById<TextView>(R.id.wrongChoice).text = insertedText
                    true
                }
                else -> false
            }
        }

    }
}