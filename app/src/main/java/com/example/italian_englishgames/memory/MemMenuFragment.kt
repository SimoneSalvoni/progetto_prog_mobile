package com.example.italian_englishgames.memory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.italian_englishgames.R


class MemMenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_mem_menu, container, false)
        val buttonsingle: Button = inflater.findViewById<Button>(R.id.startbuttonsingle)
        val infoButton = inflater.findViewById<Button>(R.id.infoButtonMem)

        buttonsingle.setOnClickListener{
            buttonsingle.findNavController().navigate(R.id.action_memMenuFragment_to_memGameFragment)
        }

        infoButton.setOnClickListener {
            val builder: AlertDialog.Builder? = activity?.let {
                AlertDialog.Builder(it)
            }
            builder?.setMessage(R.string.info_memory)
            builder?.setNegativeButton("Chiudi"){ _, _ -> }
            builder?.create()
            builder?.show()
        }
        return inflater
    }
}