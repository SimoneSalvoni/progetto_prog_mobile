package com.example.italian_englishgames.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.italian_englishgames.MainActivity
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button
    private lateinit var newAccountBtn: Button
    private lateinit var emailErr:TextView
    private lateinit var passwordErr:TextView
    private lateinit var otherErr:TextView

    private val registerRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, intent)
            finish()
        }
        else {
            val intent = Intent(this, MainActivity::class.java)
            setResult(RESULT_CANCELED, intent)
            finish()
        }
    }


    //per abilitare il pulsante solo quando email e password sono scritti
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {checkEnableButton()}
        override fun afterTextChanged(s: Editable) {}
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            auth= Firebase.auth
            loginBtn = findViewById(R.id.btn)
            newAccountBtn = findViewById(R.id.newaccount)
            email = findViewById(R.id.email)
            password = findViewById(R.id.password)
            emailErr = findViewById(R.id.errorEmailLogin)
            passwordErr = findViewById(R.id.errorEmailLogin)
            otherErr = findViewById(R.id.generalErrorLogin)

            email.addTextChangedListener(textWatcher)
            password.addTextChangedListener(textWatcher)
            newAccountBtn.setOnClickListener {
                intent = Intent(this, RegisterActivity::class.java)
                registerRequest.launch(intent)
            }
            loginBtn.setOnClickListener {
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            val intent = Intent(this, MainActivity::class.java)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                        else {
                            val errorCode = (task.exception as FirebaseAuthException?)!!.errorCode
                            checkLoginError(errorCode)
                        }
                    }
            }
        }

    private fun checkEnableButton(){ loginBtn.isEnabled = (password.text.toString()!="")&&(email.text.toString()!="") }

    /**
     * Questa funzione mostra testi di errore di fronte a errori di autenticazione
     *
     * @param errorCode è il codice di errore che firebase ci restituisce
     */
    private fun checkLoginError(errorCode: String){
        emailErr.text=""
        passwordErr.text=""
        otherErr.text=""
        when(errorCode){
            "ERROR_INVALID_CREDENTIAL"-> otherErr.text="The supplied auth credential is malformed or has expired"
            "ERROR_INVALID_EMAIL" ->  emailErr.text = "The email address is badly formatted"
            "ERROR_WRONG_PASSWORD" -> passwordErr.text ="The password is invalid"
            "ERROR_USER_NOT_FOUND" -> otherErr.text="There is no user record corresponding to this identifier. The user may have been deleted."
            else-> {
                val intent = Intent(this, MainActivity::class.java)
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }
    }
}