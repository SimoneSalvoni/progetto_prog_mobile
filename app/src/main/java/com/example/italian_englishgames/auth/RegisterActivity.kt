package com.example.italian_englishgames.auth

import android.content.Intent
import android.os.Bundle
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


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var regBtn: Button
    private lateinit var emailErr: TextView
    private lateinit var passwordErr: TextView
    private lateinit var otherErr: TextView

    private val finishRegistrationRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val replyIntent = Intent(this, MainActivity::class.java)
            setResult(RESULT_OK, replyIntent)
            finish()
        }
        else{
            val replyIntent = Intent(this, MainActivity::class.java)
            setResult(RESULT_CANCELED, replyIntent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        regBtn = findViewById(R.id.register)
        emailErr = findViewById(R.id.errorEmailReg)
        passwordErr = findViewById(R.id.errorEmailReg)
        otherErr = findViewById(R.id.generalErrorReg)

        email.setOnFocusChangeListener {_,_ -> checkEnableButton()}
        password.setOnFocusChangeListener {_,_ -> checkEnableButton()}
        regBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this){ task->
                    if(task.isSuccessful) {
                        // regBtn.findNavController().navigate(R.id.action_firstRegistrationFragment_to_usernamePhotoSelectionFragment)
                        val intent = Intent(this, CompleteRegisterActivity::class.java)
                        finishRegistrationRequest.launch(intent)
                    }
                    else{
                        val errorCode = (task.exception as FirebaseAuthException?)!!.errorCode
                        checkLoginError(errorCode)
                    }
                }
        }
    }

    private fun checkEnableButton(){
        if( (password.text.toString()!="")&&(email.text.toString()!="") ) regBtn.isEnabled = true
    }

    private fun checkLoginError(errorCode: String){
        emailErr.text=""
        passwordErr.text=""
        otherErr.text=""
        when(errorCode){
            "ERROR_INVALID_CREDENTIAL"-> otherErr.text="The supplied auth credential is malformed or has expired"
            "ERROR_INVALID_EMAIL" ->  emailErr.text = "The email address is badly formatted"
            "ERROR_WRONG_PASSWORD" -> passwordErr.text ="The password is invalid"
            "ERROR_EMAIL_ALREADY_IN_USE"->emailErr.text="The email address is already in use by another account."
            "ERROR_WRONG_PASSWORD"->passwordErr.text="The password is invalid"
            else-> {
                val intent = Intent(this, LoginActivity::class.java)
                setResult(RESULT_CANCELED, intent)
                finish()
            }
        }
    }

}