package com.example.italian_englishgames

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class ContattiActivity : AppCompatActivity() {

    private lateinit var email: ImageButton
    private lateinit var site: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatti)


        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }


        email = findViewById(R.id.imageEmail)
        site = findViewById(R.id.imageSite)

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                val to = Array<String>(1) { "email@email.com" }
                putExtra(Intent.EXTRA_EMAIL, to)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else Toast.makeText(
                this,
                "Applicazione per gestire le e-mail non trovata",
                Toast.LENGTH_SHORT
            )
                .show()

        }


        site.setOnClickListener {
            val webpage = Uri.parse("https://univpm.it/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else Toast.makeText(
                this,
                "Browser non trovato, impossibile aprire il link",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }
}