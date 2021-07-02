package com.example.italian_englishgames


import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.italian_englishgames.auth.LoginActivity
import com.example.italian_englishgames.boggle.BoggleActivity
import com.example.italian_englishgames.impiccato.ImpActivity
import com.example.italian_englishgames.memory.MemActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    //private lateinit var cm: ConnectivityManager
    private lateinit var storage: FirebaseStorage
    private val loginRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            /*
            val currentUser= auth.currentUser
            val username = findViewById<TextView>(R.id.usernameMain)
            val image = findViewById<ImageView>(R.id.userImgMain)

            username.text = currentUser!!.displayName
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

             */
        }
        else{
            Toast.makeText(applicationContext, "C'è stato un errore nell'autenticazione, ritenta", Toast.LENGTH_LONG).show()
           // val intent = Intent(this, LoginActivity::class.java)
            //loginRequest.launch(intent) problema di ricorsione, boh
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= Firebase.auth
        storage=Firebase.storage
/*
//RITORNACI SE ABBIAMO TEMPO
        cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworks: NetworkInfo? = cm.activeNetworkInfo
        if(!activeNetworks?.isConnectedOrConnecting!!){
            val intent = Intent(this, NoConnectionActivity::class.java)
        }

 */

        //setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
       // NavigationUI.setupActionBarWithNavController(this, view.find)
        val buttonimp = findViewById<Button>(R.id.buttonImp)
        buttonimp.setOnClickListener{
            val intent = Intent(this, ImpActivity::class.java)
            startActivity(intent)
        }
        val buttonMem = findViewById<Button>(R.id.buttonMem)
        buttonMem.setOnClickListener {
            val intent = Intent (this, MemActivity::class.java)
            startActivity(intent)
        }
        val buttonBoggle = findViewById<Button>(R.id.boggleBtn)
        buttonBoggle.setOnClickListener {
            val intent = Intent(this, BoggleActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onStart(){
        super.onStart()
        val currentUser= auth.currentUser
        if (currentUser==null) {
            val intent = Intent(this, LoginActivity::class.java)
            loginRequest.launch(intent)
        }
        else{
            /*
            //PLACEHOLDER
            val username = findViewById<TextView>(R.id.usernameMain)
            val image = findViewById<ImageView>(R.id.userImgMain)
            val storageRef = storage.reference
            val imageRef = storageRef.child(currentUser.uid)
            val maxSize: Long = 1024*1024*20

            username.text=currentUser.displayName
            imageRef.getBytes(maxSize).addOnSuccessListener {
                Glide.with(this)
                    .asBitmap()
                    .load(it)
                    .centerCrop()
                    .into(image)
                // .placeholder(R.drawable.)
            }


             */
        }
    }
/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    */
}