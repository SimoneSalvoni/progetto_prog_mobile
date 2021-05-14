package com.example.italian_englishgames.impiccato

import android.os.Bundle
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentImpGameBinding
import com.google.android.material.snackbar.Snackbar

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
         //binding = inflater.inflate(R.layout.fragment_imp_game, container, false)
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_imp_game,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.impViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.chooseWord()
        binding.impImageView.setImageResource(R.drawable.imp00)

        inputText()


    }

    fun inputText(){
        binding.guessText.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = LayoutInflater
            val dialogLayout = inflater.inflate(R.layout.imp_input_prompt)
            val inputText = dialogLayout.FindViewById<EditText>(R.id.inputLetter)

            with(builder) {

                setTitle("Inserisci una lettera")
                setPositiveButton("OK"){dialog, which->
                    val car = dialogLayout.inputLetter.text.toString() as Char
                    if (car !in 'A'..'Z' && car !in 'a'..'z')
                        Snackbar.make(binding.guessText, "Inserisci una lettera", Snackbar.LENGTH_SHORT)
                            .show()
                    else if (!viewModel.ChosenLetterUsable(car))
                        Snackbar.make(
                            binding.guessText,
                            "Lettera già utilizzata",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    else {
                        viewModel.checkLetter(car)
                        checkErrors()
                        checkGameState()
                    }
                }
                setNegativeButton("Annulla"){
                    dialogLayout.FindViewById<EditText>(R.id.inputLetter).text = ""
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    fun checkErrors(){
        var impiccato = binding.impImageView
        when(viewModel.errors.value){
            1 -> impiccato.setImageResource(R.drawable.imp01)
            2 -> impiccato.setImageResource(R.drawable.imp02)
            3 -> impiccato.setImageResource(R.drawable.imp03)
            4 -> impiccato.setImageResource(R.drawable.imp04)
            5 -> impiccato.setImageResource(R.drawable.imp05)
            6 -> impiccato.setImageResource(R.drawable.imp06)
        }
    }

    fun checkGameState(){
        when(viewModel.gameState.value){
            ImpViewModel.State.WIN -> view?.findNavController()?.navigate(R.id.action_impGameFragment_to_impWinFragment)
            ImpViewModel.State.LOSE -> view?.findNavController()?.navigate(R.id.action_impGameFragment_to_impLoseFragment)
        }
    }

}