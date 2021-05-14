package com.example.italian_englishgames.impiccato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.italian_englishgames.R


class ImpMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_imp_menu, container, false)
        val button: Button = inflater.findViewById<Button>(R.id.startbutton)
        button.setOnClickListener{
            button.findNavController().navigate(R.id.action_impMenuFragment_to_impGameFragment)
        }
        return inflater

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}