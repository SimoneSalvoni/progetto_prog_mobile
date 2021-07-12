package com.example.italian_englishgames.boggle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class BoggleWinFragment : Fragment() {

    private val args: BoggleWinFragmentArgs by navArgs()
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var newRecordText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater = inflater.inflate(R.layout.fragment_boggle_win, container, false)
        inflater.findViewById<TextView>(R.id.pointsBoggleWin).text = args.finalPoints.toString()
        inflater.findViewById<TextView>(R.id.foundWordsBoggleWin).text = args.finalFoundWordsList
        val retry = inflater.findViewById<Button>(R.id.retryButton)
        retry.setOnClickListener {
            retry.findNavController().navigate(R.id.action_boggleWinFragment_to_boggleGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener {
            home.findNavController().navigate(R.id.action_boggleWinFragment_to_boggleMenuFragment)
        }

        db = Firebase.firestore
        auth = Firebase.auth
        newRecordText = inflater.findViewById(R.id.newRecordBoggle)
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser = auth.currentUser
        db.collection("userRecords").document(currentUser!!.uid)
            .get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val document = it.result
                    val record: Int? = document!!.get("boggleMaxPoints", Int::class.java)
                    if (args.finalPoints > record!!) {
                        newRecordText.text = "NUOVO RECORD!"
                        db.collection("userRecords")
                            .document(currentUser.uid).update("boggleMaxPoints", args.finalPoints)
                    }
                }
            }
    }
}