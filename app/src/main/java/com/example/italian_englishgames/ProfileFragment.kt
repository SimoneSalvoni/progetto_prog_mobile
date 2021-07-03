package com.example.italian_englishgames

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
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
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser= auth.currentUser
        val username = findViewById<TextView>(R.id.userName)
        val image = findViewById<ImageView>(R.id.imageView)
        val impRecord = findViewById<TextView>(R.id.impRecord)
        val memRecord = findViewById<TextView>(R.id.memRecord)
        val bogRecord = findViewById<TextView>(R.id.bogRecord)
        val fab = findViewById<FloatingActionButton>(R.id.editButton)


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
                    val record1: String = document.get("memBestTime", String::class.java)
                    impRecord.text = record1
                    val record2: String = document.get("memBestTime", String::class.java)
                    memRecord.text = record2
                    val record3: Int? = document.get("boggleMaxPoints", Int::class.java)
                    bogRecord.value = record3
                }
            }

        fab.setOnClickListener{
            //navigation to mod
        }


    }

}