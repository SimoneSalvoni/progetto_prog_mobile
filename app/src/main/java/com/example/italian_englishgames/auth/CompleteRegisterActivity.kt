package com.example.italian_englishgames.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class CompleteRegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var username: EditText
    private lateinit var img: ImageView
    private lateinit var imgUri: Uri
    private lateinit var btn: Button
    private lateinit var imgbtn: Button


    private val imgRequest = registerForActivityResult(ActivityResultContracts.OpenDocument()){
        if (it != null) {
            img.setImageURI(it)
            imgUri = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_register)
        auth = Firebase.auth
        username = findViewById(R.id.username)
        img = findViewById(R.id.img)
        btn = findViewById(R.id.btn)
        imgbtn = findViewById(R.id.imgbtn)


        imgbtn.setOnClickListener {
            imgRequest.launch(arrayOf("image/*"))
        }

        btn.setOnClickListener {
            val currentUser = auth.currentUser
            val userInfo = userProfileChangeRequest {
                displayName = username.text.toString()
                photoUri = imgUri
            }

            currentUser!!.updateProfile(userInfo)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){
                        var intent = Intent(this, RegisterActivity::class.java)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
        }
    }

    private fun checkEnableButton(hasFocus:Boolean){
        if(!hasFocus){
            if((username.text.toString()!="")) btn.isEnabled = true
        }
    }
}