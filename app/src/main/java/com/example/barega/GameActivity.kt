// GameActivity.kt
package com.example.barega

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        Utils.setTextViewBackgroundColor(this, "questionColorSection", 255, 255, 255)
    }
}
