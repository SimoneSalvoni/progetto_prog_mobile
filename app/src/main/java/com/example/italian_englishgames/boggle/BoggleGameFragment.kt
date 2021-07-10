package com.example.italian_englishgames.boggle

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentBoggleGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.properties.Delegates


class BoggleGameFragment : Fragment() {

    lateinit var binding: FragmentBoggleGameBinding
    private val viewModel: BoggleViewModel by viewModels()
    private lateinit var checkBtn: Button
    private lateinit var cancBtn: Button
    private lateinit var timeLeftText: TextView
    private lateinit var points: TextView
    private lateinit var foundWordsList: TextView
    private var word=""
    private val isChosen = BooleanArray(16){false}
    private var timeLeft: Long = 60000
    private var endTime by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            endTime = savedInstanceState.getLong("endTime")
            timeLeft = endTime - System.currentTimeMillis();
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boggle_game, container, false)
        val toolbar: Toolbar = binding.mainToolbar
        toolbar.setNavigationOnClickListener {
            toolbar.findNavController().navigate(R.id.action_boggleGameFragment_to_boggleMenuFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.boggleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        checkBtn=binding.checkWordButton
        cancBtn=binding.cancelBtn
        timeLeftText = binding.timeLeft
        points = binding.pointsBoggleGame
        foundWordsList = binding.foundWordsBoggle


        viewModel.startGame()
        setLetters(viewModel.letters)

        /**
         * Qua viene fatto il setup delle varie caselle del boggle
         */
        for (i in 0 until binding.gridLayout.childCount){
            (binding.gridLayout.getChildAt(i) as Button).setOnClickListener {
                val index= (it.parent as GridLayout).indexOfChild(it)
                isChosen[index]=true
                it.setBackgroundResource(R.drawable.boggle_button_border)
                enableNearButtons(index)
                word=word.plus((it as Button).text)
            }
        }

        checkBtn.setOnClickListener {
            lifecycleScope.launch{checkWord(word)}
            word=""
            for (i in 0..15) isChosen[i]=false
        }
        cancBtn.setOnClickListener {
            enableAll()
            word=""
            for (i in 0..15) isChosen[i]=false
        }

        /**
         * Quando la variabile ready del viewModel diventa true bisogna far partire il countdown
         * perché il carimento delle parole da file è finito
         */
        viewModel.ready.observe(viewLifecycleOwner, {
            if (it) {
                enableAll()
                checkBtn.isEnabled = true
                cancBtn.isEnabled = true
                timeLeftText.isVisible = true
                points.isVisible = true
                foundWordsList.isVisible = true
                binding.textView3.isVisible = true
                binding.tempo.isVisible = true
                binding.loadingBoggle.isVisible = false

                endTime=System.currentTimeMillis() + timeLeft
                object : CountDownTimer(timeLeft, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeLeft = millisUntilFinished
                        timeLeftText.text = (millisUntilFinished / 1000).toString()
                    }

                    /**
                     * A fine countdown finisce la partita
                     */
                    override fun onFinish() {
                        val finalPoints = (binding.pointsBoggleGame.text as String).toInt()
                        val finalFoundWordList = binding.foundWordsBoggle.text.toString()
                        val action =
                            BoggleGameFragmentDirections.actionBoggleGameFragmentToBoggleWinFragment(
                                finalPoints,
                                finalFoundWordList
                            )
                        requireView().findNavController().navigate(action)
                    }
                }.start()
            }
        })
    }

    /**
     * Per mantenere il countdown quando si gira il dispositivo
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong("timeLeft", timeLeft)
        outState.putLong("endTime", endTime)
        super.onSaveInstanceState(outState)
    }

    /**
     * Questa funzione inserisce le lettere prese dal viewModel nelle caselle
     *
     * @param letters è l'Array che contiene le lettere
     */
    private fun setLetters(letters: Array<String>){
        var btn: Button
       for (i in 0 until binding.gridLayout.childCount){
           btn = binding.gridLayout.getChildAt(i) as Button
           btn.text=letters[i]
       }
    }

    /**
     * Questa funzione abilita il click a tutte le caselle
     */
    private fun enableAll(){
        var btn: Button
        for (i in 0 until binding.gridLayout.childCount){
            btn = binding.gridLayout.getChildAt(i) as Button
            btn.isEnabled = true
            btn.setBackgroundResource(R.drawable.boggle_button_no_border)
        }
    }

    /**
     * Questa funziona abilita il click solo alle caselle adiacenti a l'ultima casella clickata
     *
     * @param index è la posizione dell'ultima casella clickata
     */
    private fun enableNearButtons(index: Int){
        //matrix[ i ][ j ] = array[ i*m + j ].
        var btn: Button
        for (i in 0 until binding.gridLayout.childCount){
            btn = binding.gridLayout.getChildAt(i) as Button
            if(!isChosen[i]) {
                if (i == index + 4 && index < 12) btn.isEnabled = true //adiacente inferiore
                else if (i == index - 4 && index > 3) btn.isEnabled = true //adiacente superiore
                else if (i == index + 1 && index != 3 && index != 7 && index != 11 && index != 15)
                    btn.isEnabled = true //adiacente destro
                else if (i == index - 1 && index != 0 && index != 4 && index != 8 && index != 12) btn.isEnabled=true //adiacente sinistro
                else if (i==index-5 && index > 3 && index != 0 && index != 4 && index != 8 && index != 12) btn.isEnabled = true //diag up sx
                else if (i==index-3 && index > 3 && index != 3 && index != 7 && index != 11 && index != 15 ) btn.isEnabled=true //diag up dx
                else if (i==index+3 && index < 12 && index != 0 && index != 4 && index != 8 && index != 12) btn.isEnabled=true //diag dwn sx
                else btn.isEnabled = i==index+5 && index < 12 && index != 3 && index != 7 && index != 11 && index != 15 //diag dwn dx o disable
            }
            else btn.isEnabled = false //disable
        }
    }

    /**
     * Questa funzione controlla se una parola formata dall'utente esiste
     *
     * @param wordToCheck è la parola da controllare
     */
   private suspend fun checkWord(wordToCheck:String){
        withContext(Dispatchers.Default){
            if(wordToCheck.isNullOrEmpty()) {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Seleziona una parola", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            else if (!viewModel.isPresent(wordToCheck.toLowerCase(Locale.ROOT))){
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Parola non esistente", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
       enableAll()
    }

}