package com.example.barega

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.math.abs
import kotlin.random.Random

@SuppressLint("DiscouragedApi")
object Utils {
    private val sharedPreferencesSettings = "SettingsPrefsFile"
    private var timeTakenInSeconds = 0
    private fun setTextViewBackgroundColor(
        context: Context,
        textViewId: String,
        red: Int,
        green: Int,
        blue: Int
    ) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as AppCompatActivity).findViewById<TextView>(resourceId)
        val backgroundColor = Color.rgb(red, green, blue)
        textView.setBackgroundColor(backgroundColor)
    }

    fun setTextViewTextColor(
        context: Context,
        textViewId: String,
        red: Int,
        green: Int,
        blue: Int
    ) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as AppCompatActivity).findViewById<TextView>(resourceId)
        val textColor = Color.rgb(red, green, blue)
        textView.setTextColor(textColor)
    }

    fun setLevelQuestionColor(
        context: Context,
    ) {
        val questionRed = getRandomRGBValues()
        val questionGreen = getRandomRGBValues()
        val questionBlue = getRandomRGBValues()
        val questionTextRed = 255 - questionRed
        val questionTextGreen = 255 - questionGreen
        val questionTextBlue = 255 - questionBlue
        levelStartTime = System.currentTimeMillis()
        setTextViewBackgroundColor(
            context,
            "questionColorSection",
            questionRed,
            questionGreen,
            questionBlue
        )
        setTextViewTextColor(
            context,
            "questionColorSection",
            questionTextRed,
            questionTextGreen,
            questionTextBlue
        )
    }

    fun setCurrentRGBColors(context: Context, textViewId: String, red: Int, green: Int, blue: Int) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.setBackgroundColor(Color.rgb(red, green, blue))
    }

    fun updateChancesLeft(context: Context, textViewId: String): Int {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                var currentChancesLeft = it.text.toString().toInt()
                currentChancesLeft = maxOf(0, currentChancesLeft - 1)
                it.text = currentChancesLeft.toString()
                return currentChancesLeft
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateChancesLeft", "NumberFormatException: ${e.message}")
            }
        }
        // Return a default value (for example, 0) in case of an error
        return 0
    }

    fun setInitialChancesLeft(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                val currentChancesLeft =
                    context.resources.getInteger(R.integer.chances_for_each_level)
                it.text = currentChancesLeft.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("setInitialChancesLeft", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun resetChancesLeft(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                val currentChancesLeft =
                    context.resources.getInteger(R.integer.chances_for_each_level)
                it.text = currentChancesLeft.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("resetChancesLeft", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun resetLevel(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                val currentLevel = 1
                it.text = currentLevel.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateLevel", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun updateLevel(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                var currentLevel = it.text.toString().toInt()
                currentLevel++
                it.text = currentLevel.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateLevel", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun getCurrentLevel(context: Context): Int {
        val resourceId = context.resources.getIdentifier("currentLevel", "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                val currentLevel = it.text.toString().toInt()
                return currentLevel
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateLevel", "NumberFormatException: ${e.message}")
            }
        }
        return 0
    }

    fun updateScore(context: Context, textViewId: String, chancesLeft: Int): Int {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                var currentScore = it.text.toString().toInt()
                currentScore += if (timeTakenInSeconds < 60) {
                    (chancesLeft * context.resources.getInteger(R.integer.value_for_each_chance)) + ((60 - timeTakenInSeconds) * 2)
                } else {
                    chancesLeft * context.resources.getInteger(R.integer.value_for_each_chance)
                }
                it.text = currentScore.toString()
                return currentScore
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateScore", "NumberFormatException: ${e.message}")
            }
        }
        // Default value to return in case of an error or if the TextView is not found
        return 0
    }

    fun resetScore(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        textView?.let {
            try {
                val currentScore = 0
                it.text = currentScore.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateScore", "NumberFormatException: ${e.message}")
            }
        }
    }

    private fun getRandomRGBValues(): Int {
        return Random.nextInt(256) // Generates a random number between 0 (inclusive) and 256 (exclusive)
    }

    fun checkLevelCleared(context: Context, difficulty: Int): Boolean {
        val levelCleared: Boolean
        val (currentQuestionRed, currentQuestionGreen, currentQuestionBlue) =
            getCurrentRGBBackgroundColor(context, "questionColorSection")
        val (currentAnswerRed, currentAnswerGreen, currentAnswerBlue) =
            getCurrentRGBBackgroundColor(context, "answerColorSection")
        val redDifference = abs(currentQuestionRed - currentAnswerRed)
        val blueDifference = abs(currentQuestionBlue - currentAnswerBlue)
        val greenDifference = abs(currentQuestionGreen - currentAnswerGreen)
        // Check if the difference is less than 50
        levelCleared =
            redDifference < (250 - difficulty) && greenDifference < (250 - difficulty) && blueDifference < (250 - difficulty)
        return levelCleared
    }

    fun showAnswerWithRGBColors(context: Context) {
        val (currentQuestionRed, currentQuestionGreen, currentQuestionBlue) =
            getCurrentRGBBackgroundColor(context, "questionColorSection")
        // Get the total height of the blueBar
        val blueBar = (context as? AppCompatActivity)?.findViewById<View>(R.id.blueBar)
        val redBar = (context as? AppCompatActivity)?.findViewById<View>(R.id.redBar)
        val greenBar = (context as? AppCompatActivity)?.findViewById<View>(R.id.greenBar)
        val greenAnswerLine =
            (context as? AppCompatActivity)?.findViewById<View>(R.id.greenAnswerLine)
        val blueAnswerLine =
            (context as? AppCompatActivity)?.findViewById<View>(R.id.blueAnswerLine)
        val redAnswerLine =
            (context as? AppCompatActivity)?.findViewById<View>(R.id.redAnswerLine)
        if (blueBar != null && greenAnswerLine != null) {
            // Calculate the vertical position based on the parameter
            val verticalPosition = 1 - (currentQuestionBlue / 255.0f)
            // Set the vertical bias for greenAnswerLine
            val params = greenAnswerLine.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = R.id.blueBar
            params.bottomToBottom = R.id.blueBar
            params.verticalBias = verticalPosition
            greenAnswerLine.layoutParams = params
            // Make the green line visible
            greenAnswerLine.visibility = View.VISIBLE
        }
        if (redBar != null && blueAnswerLine != null) {
            // Calculate the vertical position based on the parameter
            val verticalPosition = 1 - (currentQuestionRed / 255.0f)
            // Set the vertical bias for blueAnswerLine
            val params = blueAnswerLine.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = R.id.blueBar
            params.bottomToBottom = R.id.blueBar
            params.verticalBias = verticalPosition
            blueAnswerLine.layoutParams = params
            // Make the blue line visible
            blueAnswerLine.visibility = View.VISIBLE
        }
        if (greenBar != null && redAnswerLine != null) {
            // Calculate the vertical position based on the parameter
            val verticalPosition = 1 - (currentQuestionGreen / 255.0f)
            // Set the vertical bias for redAnswerLine
            val params = redAnswerLine.layoutParams as ConstraintLayout.LayoutParams
            params.topToTop = R.id.greenBar
            params.bottomToBottom = R.id.greenBar
            params.verticalBias = verticalPosition
            redAnswerLine.layoutParams = params
            // Make the blue line visible
            redAnswerLine.visibility = View.VISIBLE
        }
    }

    fun getCurrentRGBBackgroundColor(context: Context, textViewId: String): Triple<Int, Int, Int> {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)
        // Default RGB values
        var red = 0
        var green = 0
        var blue = 0
        textView?.let {
            val background = it.background
            if (background is ColorDrawable) {
                val currentColor = background.color
                red = Color.red(currentColor)
                green = Color.green(currentColor)
                blue = Color.blue(currentColor)
            }
        }
        return Triple(red, green, blue)
    }

    fun setInitialBarColors(
        context: Context,
    ) {
        val barRed = 255
        val barGreen = 255
        val barBlue = 255
        setTextViewBackgroundColor(
            context,
            "redBar",
            barRed,
            0,
            0
        )
        setTextViewBackgroundColor(
            context,
            "greenBar",
            0,
            barGreen,
            0
        )
        setTextViewBackgroundColor(
            context,
            "blueBar",
            0,
            0,
            barBlue
        )
    }

    fun checkTimeTakenToClearLevel() {
        levelEndTime = System.currentTimeMillis()
        val timeTakenInMillis = levelEndTime - levelStartTime
        // Convert milliseconds to seconds for better readability
        timeTakenInSeconds = (timeTakenInMillis / 1000).toInt()
        println("Time taken to clear the level: $timeTakenInSeconds seconds")
    }
    // Function to update the player name in SharedPreferences
    fun updatePlayerNameInPrefs(context: Context, newPlayerName: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(sharedPreferencesSettings, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("currentPlayerName", newPlayerName)
        editor.apply()
    }
    // Function to retrieve the current player name from SharedPreferences
    fun getCurrentPlayerNameFromPrefs(context: Context): String {
        val prefs: SharedPreferences =
            context.getSharedPreferences(sharedPreferencesSettings, Context.MODE_PRIVATE)
        return prefs.getString("currentPlayerName", "") ?: ""
    }
}