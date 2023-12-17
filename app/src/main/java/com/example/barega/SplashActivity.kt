package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 seconds
    private val DELAY_AFTER_START: Long = 10000 // 10 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // Start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Pause for 10 seconds before finishing the activity
            Handler().postDelayed({
                finish()
            }, DELAY_AFTER_START)

        }, SPLASH_TIME_OUT)
    }
}
