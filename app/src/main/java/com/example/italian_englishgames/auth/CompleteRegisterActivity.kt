package com.example.italian_englishgames.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.File

class CompleteRegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var db: FirebaseFirestore
    private lateinit var username: EditText
    private lateinit var img: ImageView
    private lateinit var imgUri: Uri
    private lateinit var btn: Button
    private lateinit var imgbtn: Button


    private val imgRequest = registerForActivityResult(ActivityResultContracts.OpenDocument()){
        if (it != null) {
            val imgPath=it.path
            val imgFile = File(imgPath)
            val maxSize=1024*1024*20
            if(imgFile.length()<=maxSize) {
                img.setImageURI(it)
                imgUri = it
            }
            else Toast.makeText(applicationContext, "Immagine troppo pesante", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_register)
        auth = Firebase.auth
        storage=Firebase.storage
        db=Firebase.firestore
        username = findViewById(R.id.username)
        img = findViewById(R.id.img)
        btn = findViewById(R.id.btn)
        imgbtn = findViewById(R.id.imgbtn)


        imgbtn.setOnClickListener {
            imgRequest.launch(arrayOf("image/*"))
        }

        btn.setOnClickListener {
            val currentUser = auth.currentUser
            val storageRef = storage.reference
            val userInfo = userProfileChangeRequest {
                displayName = username.text.toString()
            }

            currentUser!!.updateProfile(userInfo)
                .addOnCompleteListener{task->
                    if(task.isSuccessful){

                        if(imgUri!=null) {
                            val imageRef = storageRef.child(currentUser.uid)
                            //val uploadTask = imageRef.putFile(imgUri)
                            imageRef.putFile(imgUri)
                            /*
                            uploadTask.addOnSuccessListener {}
                            uploadTask.addOnFailureListener {}
                            NON DOVREBBERO SERVIRE...
                             */
                        }

                        val userRecordsInit = hashMapOf(
                            "impMaxStreak" to 0,
                            "memBestTime" to "",
                            "boggleMaxPoints" to 0
                        )
                        db.collection("userRecords").document(currentUser.uid)
                            .set(userRecordsInit)
                            .addOnSuccessListener {}
                            .addOnFailureListener {}
                        var intent = Intent(this, RegisterActivity::class.java)
                        setResult(RESULT_OK, intent)
                        finish()
                    }
                    else{
                        var intent = Intent(this, RegisterActivity::class.java)
                        setResult(RESULT_CANCELED, intent)
                        finish()
                    }
                }
        }
    }

}