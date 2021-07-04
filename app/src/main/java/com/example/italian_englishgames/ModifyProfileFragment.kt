package com.example.italian_englishgames

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class ModifyProfileFragment : Fragment() {


    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private  var imgUri: Uri ?= null
    private lateinit var image: ImageView
    private val imgRequest = requireActivity().registerForActivityResult(ActivityResultContracts.GetContent()){
        image.setImageURI(it)
        imgUri=it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= Firebase.auth
        storage= Firebase.storage

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val Layout = inflater.inflate(R.layout.fragment_modify_profile, container, false)
        val toolbar: Toolbar = Layout.findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
        }
        return  Layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentUser= auth.currentUser
        val username = view.findViewById<TextView>(R.id.userName)
        image = view.findViewById(R.id.imageView)
        val password = view.findViewById<EditText>(R.id.editPassword)
        val email = view.findViewById<EditText>(R.id.editEmail)
        val confPass = view.findViewById<EditText>(R.id.confirmPassword)
        val fab = view.findViewById<FloatingActionButton>(R.id.saveEdit)
        val button = view.findViewById<FloatingActionButton>(R.id.editImage)


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
            val profileUpdates = userProfileChangeRequest {
                displayName = username.text.toString()
                if(imgUri != null)photoUri = imgUri
            }
            currentUser.updateProfile(profileUpdates)

            if(password.text.toString() == "" && confPass.text.toString()=="")
                fab.findNavController().navigate(R.id.action_modifyProfileFragment_to_profileFragment)
            else if(password.text==confPass.text){
                currentUser.updatePassword(password.text.toString())
                fab.findNavController().navigate(R.id.action_modifyProfileFragment_to_profileFragment)
            }
            else{
                /*
                Snackbar.make(
                    Layout,
                    "Password non corrispondente!",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                 */
                Toast.makeText(requireContext(), "Password non corrispondete!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        button.setOnClickListener{
            imgRequest.launch("image/*")
        }


    }


}