package com.example.italian_englishgames.impiccato

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


class ImpLoseFragment : Fragment() {
    val arg: ImpLoseFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_imp_lose, container, false)
        inflater.findViewById<TextView>(R.id.solution).text=arg.word
        val retry = inflater.findViewById<Button>(R.id.retryButton)
        retry.setOnClickListener{
            retry.findNavController().navigate(R.id.action_impLoseFragment_to_impGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_impLoseFragment_to_impMenuFragment)
        }
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}
