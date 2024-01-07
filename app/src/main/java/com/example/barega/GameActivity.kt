// GameActivity.kt
package com.example.barega

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        Utils.setInitialChancesLeft(this, "chancesLeft")
        Utils.setLevelQuestionColor(this)
        Utils.setInitialBarColors(this)
        // Show Answer lines
        Utils.showAnswerWithRGBColors(
            this, 0.5f
        )
    }
}
