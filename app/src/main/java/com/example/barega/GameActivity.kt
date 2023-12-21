package com.example.barega

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val questionColorView = findViewById<TextView>(R.id.questionColorSection)
        // Set the question color
        questionColorView.setBackgroundColor(Color.rgb(128, 0, 128))
    }
}
