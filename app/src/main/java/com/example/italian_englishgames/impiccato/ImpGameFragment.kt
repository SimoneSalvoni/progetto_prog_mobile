package com.example.italian_englishgames.impiccato

import android.content.Context
import android.os.Bundle
import android.os.VibrationAttributes
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import com.example.italian_englishgames.R

class ImpGameFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imp_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        lateinit var GameModel: ImpViewModel

        /*Log.i("GameFragment", "Called ViewModelProvider.get")
        GameModel = ViewModelProvider(this).get(ImpViewModel::class.java)

        findViewById<TextView>(R.id.displayedText).text = GameModel.shownword.get()
        findViewById<TextView>(R.id.wrongChoice).text = GameModel.chosenLetters.get()*/

        findViewById<Button>(R.id.guessButton).setOnClickListener { view ->
            val builder = AlertDialog.Builder(this)
            val inflater= LayoutInflater(Context.getSystemService)
            val dialogLayout = inflater.inflate(R.layout.imp_guess_layout)
            val guessText = dialogLayout.findViewById<TextView>(R.id.guessText)

            with(builder) {
                setTitle("Scrivi una lettera")
                setPositiveButton("OK") {dialog, which ->
                    findViewById<TextView>(R.id.displayedText).text = guessText.text.tostrng
                }
                setNegativeButton("Cancella") {dialog, which ->
                    Log.d("Main","Annullato")
                }
                setView(dialogLayout)
                show()
                }
            }
        }
    }
}