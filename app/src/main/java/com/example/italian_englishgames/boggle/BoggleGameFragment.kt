package com.example.italian_englishgames.boggle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.gridlayout.widget.GridLayout
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.lifecycleScope
import com.example.italian_englishgames.R
import com.example.italian_englishgames.databinding.FragmentBoggleGameBinding
import com.example.italian_englishgames.databinding.FragmentMemGameBinding
import kotlinx.coroutines.launch


class BoggleGameFragment : Fragment() {

    lateinit var binding: FragmentBoggleGameBinding
    private val viewModel: BoggleViewModel by viewModels()
    private lateinit var checkBtn: Button
    private lateinit var cancBtn: Button
    private lateinit var timeText: TextView
    private var word=""

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
        timeText = binding.time

        viewModel.startGame()
        setLetters(viewModel.letters)

        for (i in 0 until binding.gridLayout.childCount){
            (binding.gridLayout.getChildAt(i) as Button).setOnClickListener {
                it.setBackgroundResource(R.drawable.boggle_button_border)
                enableNearButtons(it as Button)
                word.plus(it.text)
            }
        }

        checkBtn.setOnClickListener {
            checkWord(word)
        }
        cancBtn.setOnClickListener {
            enableAll()
            word=""
        }

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
        word=""
        for (i in 0 until binding.gridLayout.childCount){
            btn = binding.gridLayout.getChildAt(i) as Button
            btn.isEnabled = true
            btn.setBackgroundResource(R.drawable.boggle_button_no_border)
        }
    }

    private fun enableNearButtons(button: Button){
        //matrix[ i ][ j ] = array[ i*m + j ].
        val index= (button.parent as GridLayout).indexOfChild(button)
        var btn: Button
        for (i in 0 until binding.gridLayout.childCount){
            btn = binding.gridLayout.getChildAt(i) as Button
            if (i==index+4 && index<12) btn.isEnabled = true //adiacente inferiore
            else if (i==index-4 && index>3) btn.isEnabled = true //adiacente superiore
            else if (i==index+1 && index!=3 && index!=7 && index!=11 && index!=15)  btn.isEnabled = true //adiacente destro
            else btn.isEnabled = i==index-1 && index!=0 && index!=4 && index!=8 && index!=12 //adiacente sinistro o disable
        }
    }

    private fun checkWord(word:String){
        lifecycleScope.launch{
            if (viewModel.isPresent(word)){
                //per ora niente, poi si vedrà
            }
            else Toast.makeText(requireContext(), "Parola già trovata!", Toast.LENGTH_SHORT).show()
        }
    }

}