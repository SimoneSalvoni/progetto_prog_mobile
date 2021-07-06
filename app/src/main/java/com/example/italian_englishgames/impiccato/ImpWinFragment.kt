package com.example.italian_englishgames.impiccato

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.italian_englishgames.ContattiActivity
import com.example.italian_englishgames.ProfileActivity
import com.example.italian_englishgames.R
import com.example.italian_englishgames.auth.LoginActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ImpWinFragment : Fragment() {

    private val arg: ImpWinFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.fragment_imp_win, container, false)
        inflater.findViewById<TextView>(R.id.solution).text=arg.word
        inflater.findViewById<TextView>(R.id.points).text=arg.points
        val retry = inflater.findViewById<Button>(R.id.retryButton)

        val toolbar: Toolbar = inflater.findViewById(R.id.menuToolbar)
        setUpToolbar(toolbar)

        retry.setOnClickListener{
            retry.findNavController().navigate(R.id.action_impWinFragment_to_impGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_impWinFragment_to_impMenuFragment)
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
            it.findNavController().navigate(R.id.action_impWinFragment_to_impMenuFragment)
        }
    }

}