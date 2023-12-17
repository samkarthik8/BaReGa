package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // 3 seconds
    private val delayAfterSTart: Long = 10000 // 10 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            // Start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Pause for 10 seconds before finishing the activity
            Handler(Looper.getMainLooper()).postDelayed({
                finish()
            }, delayAfterSTart)

        }, splashTimeOut)
    }
}
