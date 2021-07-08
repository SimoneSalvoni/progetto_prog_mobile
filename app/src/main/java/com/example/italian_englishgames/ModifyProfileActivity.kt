package com.example.italian_englishgames

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.italian_englishgames.boggle.BoggleActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ModifyProfileActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var image: ImageView
    private  var imgUri: Uri ?= null
    private val imgRequest = registerForActivityResult(ActivityResultContracts.GetContent()){
        image.setImageURI(it)
        imgUri=it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        storage= Firebase.storage
        setContentView(R.layout.activity_modify_profile)
        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser= auth.currentUser
        val username = findViewById<TextView>(R.id.editUsername)
        image = findViewById<ImageView>(R.id.imgToEdit)
        val password = findViewById<EditText>(R.id.editPassword)
        val email = findViewById<EditText>(R.id.editEmail)
        val confPass = findViewById<EditText>(R.id.confirmPassword)
        val fab = findViewById<FloatingActionButton>(R.id.saveEdit)
        val button = findViewById<Button>(R.id.editImage)


        username.text = currentUser!!.displayName
        email.setText(currentUser.email)
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

        fab.setOnClickListener{
            currentUser.updateEmail(email.text.toString())
            val intent = Intent(this, ProfileActivity::class.java)
            val profileUpdates = userProfileChangeRequest {
                displayName = username.text.toString()
                if(imgUri != null)photoUri = imgUri
            }
            currentUser.updateProfile(profileUpdates)

            if(password.text.toString() == "" && confPass.text.toString()=="")
            startActivity(intent)

            else if(password.text==confPass.text){
                currentUser.updatePassword(password.text.toString())
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Password non corrispondente!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        button.setOnClickListener{
            imgRequest.launch("image/*")
        }

    }


}