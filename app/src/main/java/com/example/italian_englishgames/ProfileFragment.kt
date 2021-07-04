package com.example.italian_englishgames

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.italian_englishgames.auth.LoginActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class ProfileFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth= Firebase.auth
        storage= Firebase.storage
        db = Firebase.firestore
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflater= inflater.inflate(R.layout.fragment_profile, container, false)
        val toolbar: Toolbar = inflater.findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
        return inflater
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser= auth.currentUser
        val username = view.findViewById<TextView>(R.id.userName)
        val image = view.findViewById<ImageView>(R.id.imageView)
        val impRecord = view.findViewById<TextView>(R.id.impRecord)
        val memRecord = view.findViewById<TextView>(R.id.memRecord)
        val bogRecord = view.findViewById<TextView>(R.id.bogRecord)
        val fab = view.findViewById<FloatingActionButton>(R.id.editButton)


        username.text = currentUser!!.displayName
        val storageRef = storage.reference
        val imageRef = storageRef.child(currentUser.uid)
        val maxSize: Long = 1024*1024*20
        imageRef.getBytes(maxSize).addOnSuccessListener {
            Glide.with(this)
                .asBitmap()
                .load(it)
                .centerCrop()
                .into(image)
            // .placeholder(R.drawable.)


        }
        db.collection("userRecords").document(currentUser.uid)
            .get().addOnCompleteListener {
                if(it.isSuccessful) {
                    val document = it.result
                    /*
                    NON ABBIAMO IMPLEMENTATO UN EFFETTIVO PUNTEGGIO PER L'IMPICCATO
                    val record1: String? = document?.get("impMaxStreak", String::class.java)
                    impRecord.text = record1

                     */
                    val record2: String? = document?.get("memBestTime", String::class.java)
                    memRecord.text = record2
                    val record3: Int? = document?.get("boggleMaxPoints", Int::class.java)
                    bogRecord.text = record3.toString()
                }
            }

        fab.setOnClickListener{
            //navigation to mod
        }


    }

}