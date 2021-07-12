package com.example.italian_englishgames.boggle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.example.italian_englishgames.ContattiActivity
import com.example.italian_englishgames.MainActivity
import com.example.italian_englishgames.ProfileActivity
import com.example.italian_englishgames.R
import com.example.italian_englishgames.auth.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class BoggleMenuFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val inflater = inflater.inflate(R.layout.fragment_boggle_menu, container, false)
        val button: Button = inflater.findViewById<Button>(R.id.startbutton)
        val infoButton = inflater.findViewById<Button>(R.id.infoButtonBoggle)
        val toolbar: Toolbar = inflater.findViewById(R.id.menuToolbar)
        setUpToolbar(toolbar)

        button.setOnClickListener{
            button.findNavController().navigate(R.id.action_boggleMenuFragment_to_boggleGameFragment)
        }

        infoButton.setOnClickListener {
            val builder: AlertDialog.Builder? = activity?.let {
                AlertDialog.Builder(it)
            }
            builder?.setMessage(R.string.info_boggle)
            builder?.setNegativeButton("Chiudi"){ _, _ -> }
            builder?.create()
            builder?.show()
        }
        return inflater
    }

    private fun setUpToolbar(toolbar: Toolbar){
        toolbar.inflateMenu(R.menu.toolbar_with_profile_menu)
        toolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.goToProfile-> startActivity(Intent(requireActivity(), ProfileActivity::class.java))
                R.id.goToContatti -> startActivity(Intent(requireActivity(), ContattiActivity::class.java))
                R.id.goToLogout ->{
                    Firebase.auth.signOut()
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
    }

}