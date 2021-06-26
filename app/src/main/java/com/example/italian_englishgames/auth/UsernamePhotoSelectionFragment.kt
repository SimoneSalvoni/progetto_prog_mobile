package com.example.italian_englishgames.auth

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.italian_englishgames.MainActivity
import com.example.italian_englishgames.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase


class UsernamePhotoSelectionFragment : Fragment() {

    private lateinit var auth:FirebaseAuth
    private lateinit var username:EditText
    private lateinit var img: ImageView
    private lateinit var imgUri: Uri
    private lateinit var btn: Button
    private lateinit var imgbtn: Button
    private val imgRequest = requireActivity().registerForActivityResult(ActivityResultContracts.GetContent()){
        img.setImageURI(it)
        imgUri=it
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate=inflater.inflate(R.layout.fragment_username_photo_selection, container, false)
        auth = Firebase.auth
        username=inflate.findViewById(R.id.username)
        img=inflate.findViewById(R.id.img)
        btn=inflate.findViewById(R.id.btn)
        imgbtn=inflate.findViewById(R.id.imgbtn)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username.setOnFocusChangeListener { _, hasFocus ->  checkEnableButton(hasFocus)}
        img.setOnFocusChangeListener { _, hasFocus ->  checkEnableButton(hasFocus)}

        imgbtn.setOnClickListener {
            imgRequest.launch("image/*")
        }

        btn.setOnClickListener {
            val user = auth.currentUser
            val profileUpdates = userProfileChangeRequest {
                displayName = username.text.toString()
                photoUri = imgUri
            }
            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "user profile updated")
                        var intent = Intent(requireActivity(), LoginActivity::class.java)
                        requireActivity().setResult(RESULT_OK, intent)
                        requireActivity().finish()
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