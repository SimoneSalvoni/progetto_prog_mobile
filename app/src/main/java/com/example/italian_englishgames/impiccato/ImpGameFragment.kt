package com.example.italian_englishgames.impiccato

import android.os.Bundle
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View {
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
           // val builder = activity.let { it1 -> AlertDialog.Builder(it1!!.applicationContext) }
            val builder = AlertDialog.Builder(this.requireContext())
            val inflater = requireActivity().layoutInflater
            val dialogLayout = inflater.inflate(R.layout.imp_input_prompt,null)
            //val dialogLayout: View = View.inflate(this.activity?.application, R.layout.imp_input_prompt, null)
            val inputText = dialogLayout.findViewById<EditText>(R.id.inputLetter)

            with(builder) {

                this.setTitle("Inserisci una lettera")
                    setPositiveButton("OK") { _, _ ->
                        var text: String? = inputText.text.toString()
                        if (text.isNullOrBlank()) {
                            Snackbar.make(
                                binding.guessText,
                                "Inserisci una lettera",
                                Snackbar.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            val car = inputText.text.toString().single()
                            if (car !in 'A'..'Z' && car !in 'a'..'z')
                                Snackbar.make(
                                    binding.guessText,
                                    "Inserisci una lettera",
                                    Snackbar.LENGTH_SHORT
                                )
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
                    }
                setNegativeButton("Annulla"){ _, _ ->
                    dialogLayout.findViewById<EditText>(R.id.inputLetter).setText("")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    fun checkErrors(){
        val impiccato = binding.impImageView
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
            ImpViewModel.State.WIN -> {
                val word = viewModel.chosenWord.value
                val action = ImpGameFragmentDirections.actionImpGameFragmentToImpWinFragment(word!!)
                requireView().findNavController().navigate(action)
            }
            ImpViewModel.State.LOSE -> {
                val word = viewModel.chosenWord.value
                val action = ImpGameFragmentDirections.actionImpGameFragmentToImpLoseFragment(word!!)
                requireView().findNavController().navigate(action)
            }
            else -> {}
        }
    }

}