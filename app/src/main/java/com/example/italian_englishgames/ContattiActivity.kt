package com.example.italian_englishgames

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class ContattiActivity : AppCompatActivity() {

    private lateinit var email: ImageButton
    private lateinit var site: ImageButton
    private lateinit var telephone: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatti)

        email = findViewById<ImageButton>(R.id.imageEmail)
        site = findViewById<ImageButton>(R.id.imageSite)
        telephone = findViewById<ImageButton>(R.id.imageTelephone)

        email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                val to = Array<String>(1) { "email@email.com" }
                putExtra(Intent.EXTRA_EMAIL, to)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }


        site.setOnClickListener {
           val webpage = Uri.parse("https://univpm.it/")
           val intent = Intent(Intent.ACTION_VIEW, webpage)
           if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
           }
        }

        telephone.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + 800123456)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(callIntent)
            }
        }

            val toolbar: Toolbar = findViewById(R.id.mainToolbar)
            toolbar.setNavigationOnClickListener {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }


    }
}