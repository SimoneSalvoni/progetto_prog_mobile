package com.example.italian_englishgames.memory

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
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
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception
import java.sql.Time


class MemWinFragment : Fragment() {

    private val arg: MemWinFragmentArgs by navArgs()
    private lateinit var newRecordText: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.fragment_mem_win, container, false)
        inflater.findViewById<TextView>(R.id.timer).text=arg.time
        val retry = inflater.findViewById<Button>(R.id.retryButton)

        val toolbar: Toolbar = inflater.findViewById(R.id.menuToolbar)
        setUpToolbar(toolbar)

        retry.setOnClickListener{
            retry.findNavController().navigate(R.id.action_memWinFragment_to_memGameFragment)
        }
        val home = inflater.findViewById<Button>(R.id.goHomeButton)
        home.setOnClickListener{
            home.findNavController().navigate(R.id.action_memWinFragment_to_memMenuFragment)
        }

        newRecordText = inflater.findViewById(R.id.newRecordMem)
        auth = Firebase.auth
        db = Firebase.firestore
        return inflater
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = auth.currentUser
        db.collection("userRecords").document(currentUser!!.uid)
            .get().addOnCompleteListener{
                if(it.isSuccessful) {
                    val record: Int? = try {
                        it.result?.get("memBestTime", String::class.java)?.replace(":", "")?.toInt()
                    } catch (e: Exception){ null }
                    if(record == null || arg.time.replace(":", "").toInt() < record!!) {
                        newRecordText.text="NUOVO RECORD!"
                        db.collection("userRecords")
                            .document(currentUser.uid).update("memBestTime", arg.time)
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
                    auth.signOut()
                    startActivity(Intent(requireActivity(), LoginActivity::class.java))
                }
            }
            true
        }
        toolbar.setNavigationOnClickListener {
            it.findNavController().navigate(R.id.action_memWinFragment_to_memMenuFragment)
        }
    }
}