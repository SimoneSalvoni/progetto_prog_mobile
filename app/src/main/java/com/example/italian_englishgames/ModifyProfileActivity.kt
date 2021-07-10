package com.example.italian_englishgames

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ModifyProfileActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var image: ImageView
    private lateinit var currentUser: FirebaseUser
    private var newImgUri: Uri? = null
    private lateinit var email: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var confPass: EditText
    private var newImageChosen = false
    private val imgRequest = registerForActivityResult(ActivityResultContracts.GetContent()) {
        image.setImageURI(it)
        newImgUri = it
        Glide.with(this)
            .asBitmap()
            .load(it)
            .centerCrop()
            .into(image)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        storage = Firebase.storage
        setContentView(R.layout.activity_modify_profile)
        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    override fun onStart() {
        super.onStart()
        currentUser = auth.currentUser!!
        username = findViewById(R.id.editUsername)
        image = findViewById(R.id.imgToEdit)
        password = findViewById(R.id.editPassword)
        email = findViewById(R.id.editEmail)
        confPass = findViewById(R.id.confirmPassword)
        val fab = findViewById<FloatingActionButton>(R.id.saveEdit)
        val button = findViewById<Button>(R.id.editImage)
        //Qua otteniamo i dati dell'utente e li inseriamo nella vista
        username.setText(currentUser!!.displayName)
        email.setText(currentUser.email)
        val storageRef = storage.reference
        val imageRef = storageRef.child(currentUser.uid)
        val maxSize: Long = 1024 * 1024 * 20
        if (!newImageChosen)
            imageRef.getBytes(maxSize).addOnSuccessListener {
                Glide.with(this)
                    .asBitmap()
                    .load(it)
                    .centerCrop()
                    .placeholder(R.drawable.default_profile)
                    .into(image)
            }

        fab.setOnClickListener {
            modifyInfo()
        }

        button.setOnClickListener {
            newImageChosen = true
            imgRequest.launch("image/*")
        }

    }

    /**
     * Questa funzione per prima cosa richiede all'utente di inserire la sua password in un popup.
     * Questo serve per la reautenticazione. Se questa va a buon fine viene fatta partire la modifica
     * dei dati, partendo dalla password
     */
    private fun modifyInfo() {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("")
        alert.setMessage("Inserisci la password per confermare i cambiamenti")
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        alert.setView(input)
        alert.setPositiveButton("Conferma") { _, _ ->
            val credential = EmailAuthProvider.getCredential(
                currentUser!!.email!!,
                input.text.toString()
            )
            currentUser!!.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        modifyPassword()
                    } else Toast.makeText(applicationContext, "Errore", Toast.LENGTH_SHORT).show()
                }
        }
        alert.setNegativeButton("Cancel") { _, _ ->
            // Canceled.
        }
        alert.show()
    }

    /**
     * Questa funzione modifica la email dell'utente
     */
    private fun modifyEmail() {
        currentUser.updateEmail(email.text.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    modifyUsername()
                }
            }
    }

    /**
     * Questa funzione modifica l'username dell'utente
     */
    private fun modifyUsername() {
        val profileUpdates = userProfileChangeRequest {
            displayName = username.text.toString()
        }
        currentUser.updateProfile(profileUpdates)
            .addOnCompleteListener {
                if (it.isSuccessful) modifyImage()
            }
    }

    /**
     * Questa funzione modifica l'immagine di profilo dell'utente se questo ne ha
     * selezionata una nuova
     */
    private fun modifyImage() {
        if (newImgUri != null) {
            val imageRef = storage.reference.child(currentUser.uid)
            imageRef.putFile(newImgUri!!)
                .addOnSuccessListener {
                    startActivity(Intent(this, ProfileActivity::class.java))
                }
        } else startActivity(Intent(this, ProfileActivity::class.java))
    }

    /**
     * Questa funzione modfica la password dell'utente se questo l'ha inserita e confermata negli appositi
     * campi della form. La modifica parte se la nuova password è uguale a quella scritta nel campo di conferma
     * e finché entrambi non sono vuoti
     */
    private fun modifyPassword() {
        if (password.text.toString().isEmpty() || confPass.text.toString().isEmpty()) modifyEmail()
        else if (password.text.toString() != confPass.text.toString())
            Toast.makeText(
                applicationContext,
                "La conferma password non coincide con la nuova password",
                Toast.LENGTH_SHORT
            ).show()
        else {
            currentUser.updatePassword(password.text.toString())
            modifyEmail()
        }
    }


}