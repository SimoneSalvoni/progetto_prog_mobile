package com.example.italian_englishgames

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class ContattiActivity : AppCompatActivity() {

    private lateinit var email1: ImageButton
    private lateinit var email2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatti)

        email1 = findViewById<ImageButton>(R.id.email1)
        email2 = findViewById<ImageButton>(R.id.email2)

        email1.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                val to = Array<String>(1){"email1@email.com"}
                putExtra(Intent.EXTRA_EMAIL, to)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
        email2.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                val to = Array<String>(1){"email2@email.com"}
                putExtra(Intent.EXTRA_EMAIL,to)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        val text = "italianenglishgames.com"
        val spannableString = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(v: View) {
                val webpage = Uri.parse("https://developer.android.com/")
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
        spannableString.setSpan(clickableSpan, 0,text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val siteText = findViewById<TextView>(R.id.site)
        siteText.text=spannableString
        siteText.movementMethod= LinkMovementMethod.getInstance()

        val toolbar: Toolbar = findViewById(R.id.mainToolbar)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



}