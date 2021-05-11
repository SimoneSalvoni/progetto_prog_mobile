package com.example.italian_englishgames.impiccato

import android.os.Bundle
import androidx.fragment.app.viewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.italian_englishgames.R

class ImpGameFragment : Fragment() {

    lateinit var binding: FragmentImpGameBinding
    val viewModel: ImpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = inflater.inflate(R.layout.fragment_imp_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = viewModel

        binding.chooseWord()
        findViewById<ImageView>(R.id.impImageView).setImageResource(R.drawable.imp00)
        findViewById<TextView>(R.id.displayedText).text = binding.shownword.get()
        findViewById<TextView>(R.id.wrongChoice).text = binding.chosenLetters.get()

        findViewById<EditText>(R.id.guessText).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    val car = binding.guessText.text.toString()
                    binding.checkLetter(car)
                    checkErrors()
                    checkGameState()
                    true
                }
                else -> false

            }
        }

    }

    fun checkErrors(){
        var impiccato = findViewById<ImageView>(R.id.impImageView)
        when(binding.errors){
            1 -> impiccato.setImageResource(R.drawable.imp01)
            2 -> impiccato.setImageResource(R.drawable.imp02)
            3 -> impiccato.setImageResource(R.drawable.imp03)
            4 -> impiccato.setImageResource(R.drawable.imp04)
            5 -> impiccato.setImageResource(R.drawable.imp05)
            6 -> impiccato.setImageResource(R.drawable.imp06)
        }
    }

    fun checkGameState(){
        when(binding.gameState){
            "WIN" -> view?.findNavController()?.navigate(R.id.action_impMenuFragment_to_impWinFragment)
            "LOSE" -> view?.findNavController()?.navigate(R.id.action_impMenuFragment_to_impLoseFragment)
        }
    }

}