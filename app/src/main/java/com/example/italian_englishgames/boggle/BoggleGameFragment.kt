package com.example.italian_englishgames.boggle

import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentBoggleGameBinding
import com.example.italian_englishgames.databinding.FragmentMemGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class BoggleGameFragment : Fragment() {

    lateinit var binding: FragmentBoggleGameBinding
    private val viewModel: BoggleViewModel by viewModels()
    private lateinit var checkBtn: Button
    private lateinit var cancBtn: Button
    private lateinit var timeLeft: TextView
    private lateinit var points: TextView
    private lateinit var foundWordsList: TextView
    private var word=""
    private val isChosen = BooleanArray(16){false}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_boggle_game, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.boggleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        checkBtn=binding.checkWordButton
        cancBtn=binding.cancelBtn
        timeLeft = binding.timeLeft
        points = binding.pointsBoggleGame
        foundWordsList = binding.foundWordsBoggle

        viewModel.startGame()
        setLetters(viewModel.letters)

        for (i in 0 until binding.gridLayout.childCount){
            (binding.gridLayout.getChildAt(i) as Button).setOnClickListener {
                val index= (it.parent as GridLayout).indexOfChild(it)
                isChosen[index]=true
                it.setBackgroundResource(R.drawable.boggle_button_border)
                enableNearButtons(it as Button, index)
                word=word.plus(it.text)
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

        viewModel.ready.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it) {
                enableAll()
                checkBtn.isEnabled = true
                cancBtn.isEnabled = true
                timeLeft.isVisible = true
                points.isVisible = true
                foundWordsList.isVisible = true
                binding.textView3.isVisible = true
                binding.tempo.isVisible = true
                binding.loadingBoggle.isVisible = false
                object : CountDownTimer(60000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeLeft.text = (millisUntilFinished / 1000).toString()
                    }

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

    private fun setLetters(letters: Array<String>){
        var btn: Button
       for (i in 0 until binding.gridLayout.childCount){
           btn = binding.gridLayout.getChildAt(i) as Button
           btn.text=letters[i]
       }
    }

    private fun enableAll(){
        var btn: Button
        for (i in 0 until binding.gridLayout.childCount){
            btn = binding.gridLayout.getChildAt(i) as Button
            btn.isEnabled = true
            btn.setBackgroundResource(R.drawable.boggle_button_no_border)
        }
    }

    private fun enableNearButtons(button: Button, index: Int){
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

   private suspend fun checkWord(wordToCheck:String){
        withContext(Dispatchers.Default){
            if (!viewModel.isPresent(wordToCheck.toLowerCase())){
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Parola non esistente", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
       enableAll()
    }

}