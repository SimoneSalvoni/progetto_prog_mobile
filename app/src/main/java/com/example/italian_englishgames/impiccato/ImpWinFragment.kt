package com.example.italian_englishgames.impiccato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.italian_englishgames.R


class ImpWinFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imp_win, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun retry (v:View){
        v.findNavController().navigate(R.id.action_impLoseFragment_to_impGameFragment)
    }

    fun goBack(v:View){
        v.findNavController().navigate(R.id.action_impLoseFragment_to_impMenuFragment)
    }

}