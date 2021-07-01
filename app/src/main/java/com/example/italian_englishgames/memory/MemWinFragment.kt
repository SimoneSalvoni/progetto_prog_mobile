package com.example.italian_englishgames.memory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.italian_englishgames.R
import com.example.italian_englishgames.impiccato.ImpWinFragmentArgs


class MemWinFragment : Fragment() {

    private val arg: MemWinFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.fragment_mem_win, container, false)
        inflater.findViewById<TextView>(R.id.timer).text=arg.time
        val retry = inflater.findViewById<Button>(R.id.retryButton)
        retry.setOnClickListener{
            retry.findNavController().navigate(R.id.action_memWinFragment_to_memGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_memWinFragment_to_memMenuFragment)
        }
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}