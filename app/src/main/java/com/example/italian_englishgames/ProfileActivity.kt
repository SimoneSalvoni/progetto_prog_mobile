package com.example.italian_englishgames

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.italian_englishgames.impiccato.ImpActivity
import com.example.italian_englishgames.memory.MemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


private lateinit var auth: FirebaseAuth
private lateinit var storage: FirebaseStorage
private lateinit var db: FirebaseFirestore

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth= Firebase.auth
        storage= Firebase.storage
        db = Firebase.firestore
        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    /**
     * Quando l'activity parte vengono erecuperati i dati dell'utente e messi nella vista
     */
    override fun onStart(){
        super.onStart()
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
                .placeholder(R.drawable.default_profile)
                .into(image)
        }
        db.collection("userRecords").document(currentUser.uid)
            .get().addOnCompleteListener {
                if(it.isSuccessful) {
                    val document = it.result

                    val record1: Int? = document?.get("impMaxStreak", Int::class.java)
                    impRecord.text = record1.toString()
                    val record2: String? = document?.get("memBestTime", String::class.java)
                    memRecord.text = record2
                    val record3: Int? = document?.get("boggleMaxPoints", Int::class.java)
                    bogRecord.text = record3.toString()
                }
            }

        fab.setOnClickListener {
            val intent = Intent(this, ModifyProfileActivity::class.java)
            startActivity(intent)
        }
    }

}