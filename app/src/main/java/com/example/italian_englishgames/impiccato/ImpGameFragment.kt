package com.example.italian_englishgames.impiccato

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.italian_englishgames.R

class ImpGameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imp_game, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // lateinit var GameModel: ImpViewModel
    }

        /*Log.i("GameFragment", "Called ViewModelProvider.get")
        GameModel = ViewModelProvider(this).get(ImpViewModel::class.java)

        findViewById<TextView>(R.id.displayedText).text = GameModel.shownword.get()
        findViewById<TextView>(R.id.wrongChoice).text = GameModel.chosenLetters.get()*/
/*
        findViewById<EditText>(R.id.guessText).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    findViewById<TextView>(R.id.wrongChoice).text = insertedText
                    true
                }
                else -> false
*/
            }


