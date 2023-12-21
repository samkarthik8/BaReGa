@file:Suppress("unused", "RedundantSuppression")

package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }
    @Suppress("unused")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish() // Finish the activity immediately after starting TitleActivity
    }
}