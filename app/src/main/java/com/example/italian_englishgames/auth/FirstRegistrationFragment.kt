package com.example.italian_englishgames.auth

import android.app.Activity.RESULT_CANCELED
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.italian_englishgames.MainActivity
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FirstRegistrationFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var regBtn: Button
    private lateinit var emailErr: TextView
    private lateinit var passwordErr: TextView
    private lateinit var otherErr: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.fragment_first_registration, container, false)
        auth = Firebase.auth
        email = inflate.findViewById(R.id.email)
        password = inflate.findViewById(R.id.password)
        regBtn = inflate.findViewById(R.id.register)
        emailErr = inflate.findViewById(R.id.errorEmailReg)
        passwordErr = inflate.findViewById(R.id.errorEmailReg)
        otherErr = inflate.findViewById(R.id.generalErrorReg)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email.setOnFocusChangeListener {_,_ -> checkEnableButton()}
        password.setOnFocusChangeListener {_,_ -> checkEnableButton()}
        regBtn.setOnClickListener {
            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(requireActivity()){ task->
                    if(task.isSuccessful)
                        regBtn.findNavController().navigate(R.id.action_firstRegistrationFragment_to_usernamePhotoSelectionFragment)
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
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                requireActivity().setResult(RESULT_CANCELED, intent)
                requireActivity().finish()
            }
        }
    }
}