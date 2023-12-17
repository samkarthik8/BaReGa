package com.example.barega

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TitleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
        val textView = findViewById<TextView>(R.id.app_name_text_view)
        val ba = "Ba"
        val re = "Re"
        val ga = "Ga"
        val coloredText = SpannableString("$ba $re $ga")
        coloredText.setSpan(
            ForegroundColorSpan(Color.BLUE), 0, ba.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        coloredText.setSpan(
            ForegroundColorSpan(Color.RED),
            ba.length + 1,
            ba.length + re.length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        coloredText.setSpan(
            ForegroundColorSpan(Color.GREEN),
            ba.length + re.length + 2,
            ba.length + re.length + ga.length + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textView.text = coloredText
    }
}