package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
        val textView = findViewById<TextView>(R.id.app_name_text_view)
        val ba = "Ba"
        val re = "Re"
        val ga = "Ga"
        val coloredText = SpannableString("$ba$re$ga")
        coloredText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_blue_light)),
            0,
            ba.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        coloredText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_red_light)),
            ba.length,
            ba.length + re.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        coloredText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_green_light)),
            ba.length + re.length,
            ba.length + re.length + ga.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = coloredText
    }

    fun startGame(view: View) {
        // Start GameActivity
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    fun quitApp(view: View) {
        finishAffinity() // This will finish the current activity and all parent activities, effectively closing the app
    }
}