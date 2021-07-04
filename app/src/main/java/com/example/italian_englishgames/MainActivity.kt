package com.example.italian_englishgames

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.italian_englishgames.auth.LoginActivity
import com.example.italian_englishgames.boggle.BoggleActivity
import com.example.italian_englishgames.impiccato.ImpActivity
import com.example.italian_englishgames.memory.MemActivity
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    //private lateinit var cm: ConnectivityManager
    private lateinit var storage: FirebaseStorage
    private lateinit var navView: NavigationView
    private lateinit var header: View
    private lateinit var drawerLayout: DrawerLayout
    private val loginRequest = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            /*
            val currentUser= auth.currentUser
            val username = findViewById<TextView>(R.id.usernameDrawer)
            val profilePic = findViewById<ImageView>(R.id.propic)
            username.text = currentUser!!.displayName
            val storageRef = storage.reference
            val imageRef = storageRef.child(currentUser.uid)
            val maxSize: Long = 1024*1024*20
            imageRef.getBytes(maxSize).addOnSuccessListener {
                Glide.with(this)
                    .asBitmap()
                    .load(it)
                    .centerCrop()
                    .into(profilePic)
                   // .placeholder(R.drawable.)
            }

             */
        }
        else{
            Toast.makeText(applicationContext, "C'è stato un errore nell'autenticazione, ritenta", Toast.LENGTH_LONG).show()
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= Firebase.auth
        storage=Firebase.storage
        navView = findViewById(R.id.nav_view)
        header= navView.getHeaderView(0)
        drawerLayout = findViewById(R.id.drawerLayout)
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

        val toolbar = findViewById<Toolbar>(R.id.mainToolbar)
        val drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.app_name, R.string.app_name)
        drawerLayout.addDrawerListener(drawerToggle)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profileOption -> startActivity(Intent(this, ProfileActivity::class.java))
                R.id.contattiOption -> startActivity(Intent(this, ContattiActivity::class.java))
                R.id.logoutOption -> {
                    auth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
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
            val x = MainActivity::class.java
            val username = header.findViewById<TextView>(R.id.usernameDrawer)
            val profilePic = header.findViewById<ImageView>(R.id.propic)
            val storageRef = storage.reference
            val imageRef = storageRef.child(currentUser.uid)
            val maxSize: Long = 1024*1024*20

            username.text=currentUser.displayName
            imageRef.getBytes(maxSize).addOnSuccessListener {
                Glide.with(this)
                    .asBitmap()
                    .load(it)
                    .centerCrop()
                    .into(profilePic)
                // .placeholder(R.drawable.)
            }
        }
    }

/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }
    */
}