package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 1000 // 1 second
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            // Start TitleActivity
            val intent = Intent(this, TitleActivity::class.java)
            startActivity(intent)
            finish() // Finish the activity immediately after starting TitleActivity
        }, splashTimeOut)
    }
}
