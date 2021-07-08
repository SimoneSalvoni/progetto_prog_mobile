package com.example.italian_englishgames.impiccato

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentImpGameBinding
import com.google.android.material.snackbar.Snackbar

class ImpGameFragment : Fragment() {
    lateinit var binding: FragmentImpGameBinding
    private val viewModel: ImpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_imp_game, container, false)
        val toolbar: Toolbar = binding.mainToolbar
        toolbar.setNavigationOnClickListener {
            toolbar.findNavController().navigate(R.id.action_impGameFragment_to_impMenuFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.impViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //se c'è un cambio di orientamento va mantenuto il timer
        if (savedInstanceState != null) {
            checkErrors()
            binding.viewTimer.base = savedInstanceState.getLong("time")
        } else {
            viewModel.chooseWord()
            binding.impImageView.setImageResource(R.drawable.imp00)
            inputText()
            binding.viewTimer.start()
        }


    }

    private fun inputText() {
        val row1 = binding.keyRow1
        val row2 = binding.keyRow2
        val row3 = binding.keyRow3
        row1.setOnClick()
        row2.setOnClick()
        row3.setOnClick()
    }

    private fun ViewGroup.setOnClick() {
        this.forEach { key ->
            if (key is TextView) {
                key.setOnClickListener {
                    if (viewModel.checkLetter(key.text.single().toLowerCase())) {
                        key.setTextColor(Color.parseColor("#00c853"))
                    } else {
                        key.setTextColor(Color.parseColor("#ef1c19"))
                    }
                    checkErrors()
                    checkGameState()
                    key.setOnClickListener {
                        Snackbar.make(
                            binding.keyRow1,
                            "Letterà già premuta",
                            Snackbar.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }

    /**
     * Cambia immagine a dipendenza del numero di errori
     */
    private fun checkErrors() {
        val impiccato = binding.impImageView
        when (viewModel.errors.value) {
            1 -> impiccato.setImageResource(R.drawable.imp01)
            2 -> impiccato.setImageResource(R.drawable.imp02)
            3 -> impiccato.setImageResource(R.drawable.imp03)
            4 -> impiccato.setImageResource(R.drawable.imp04)
            5 -> impiccato.setImageResource(R.drawable.imp05)
            6 -> impiccato.setImageResource(R.drawable.imp06)
        }
    }

    /**
     * Lo stato del gioco è tenuto nel ViewModel. Se l'utente ha trovato la parola è posto a WIN
     * e si passa alla schermata di vittora. Se l'utente fa 6 errori lo stato è posto a LOSE e
     * si passa alla schermata di sconfitta
     */
    private fun checkGameState() {
        when (viewModel.gameState.value) {
            ImpViewModel.State.WIN -> {
                val word = viewModel.chosenWord.value
                val points = viewModel.pointsCalc(word!!.count(), binding.viewTimer.text.toString())
                val action = ImpGameFragmentDirections.actionImpGameFragmentToImpWinFragment(
                    word,
                    points
                )
                requireView().findNavController().navigate(action)
            }
            ImpViewModel.State.LOSE -> {
                val word = viewModel.chosenWord.value
                val action =
                    ImpGameFragmentDirections.actionImpGameFragmentToImpLoseFragment(word!!)
                requireView().findNavController().navigate(action)
            }
            else -> {
            }
        }
    }

}