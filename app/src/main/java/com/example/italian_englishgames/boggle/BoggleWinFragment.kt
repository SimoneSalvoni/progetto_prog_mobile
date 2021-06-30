package com.example.italian_englishgames.boggle

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


class BoggleWinFragment : Fragment() {

    val args: BoggleWinFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_boggle_win, container, false)
        inflater.findViewById<TextView>(R.id.pointsBoggleWin).text=args.finalPoints.toString()
        inflater.findViewById<TextView>(R.id.foundWordsBoggleWin).text=args.finalFoundWordsList
        val retry = inflater.findViewById<Button>(R.id.retryButton)
        retry.setOnClickListener {
            retry.findNavController().navigate(R.id.action_boggleWinFragment_to_boggleGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_boggleWinFragment_to_boggleMenuFragment)
        }
        return inflater
    }

}