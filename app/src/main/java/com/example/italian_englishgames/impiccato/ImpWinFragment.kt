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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class ImpWinFragment : Fragment() {

    private val args: ImpWinFragmentArgs by navArgs()
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var newRecordText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.fragment_imp_win, container, false)
        inflater.findViewById<TextView>(R.id.solution).text=args.word
        inflater.findViewById<TextView>(R.id.points).text=args.points
        val retry = inflater.findViewById<Button>(R.id.retryButton)

        val toolbar: Toolbar = inflater.findViewById(R.id.menuToolbar)
        setUpToolbar(toolbar)

        db = Firebase.firestore
        auth = Firebase.auth
        newRecordText = inflater.findViewById(R.id.newRecordImp)

        retry.setOnClickListener{
            retry.findNavController().navigate(R.id.action_impWinFragment_to_impGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_impWinFragment_to_impMenuFragment)
        }
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = auth.currentUser
        db.collection("userRecords").document(currentUser!!.uid)
            .get().addOnCompleteListener{
                if(it.isSuccessful) {
                    val document = it.result
                    val record: Int? = document!!.get("impMaxStreak", Int::class.java)
                    if(args.points.toInt() > record!!) {
                        newRecordText.text="NUOVO RECORD!"
                        db.collection("userRecords")
                            .document(currentUser.uid).update("impMaxStreak", args.points.toInt())
                    }
                }
            }
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