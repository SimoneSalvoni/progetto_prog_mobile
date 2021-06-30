package com.example.italian_englishgames.boggle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.italian_englishgames.R


class BoggleMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_boggle_menu, container, false)
        val playBtn = inflater.findViewById<Button>(R.id.playBtn)
        playBtn.setOnClickListener {
            playBtn.findNavController().navigate(R.id.action_boggleMenuFragment_to_boggleGameFragment)
        }
        return inflater
    }

}