package com.example.barega

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.random.Random

@SuppressLint("DiscouragedApi")
object Utils {
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

    fun updateChancesLeft(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)

        textView?.let {
            try {
                var currentChancesLeft = it.text.toString().toInt()
                currentChancesLeft--
                it.text = currentChancesLeft.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateChancesLeft", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun setInitialChancesLeft(context: Context, textViewId: String) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)

        textView?.let {
            try {
                var currentChancesLeft =
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

    fun updateScore(context: Context, textViewId: String, chancesLeft: Int) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)

        textView?.let {
            try {
                var currentScore = it.text.toString().toInt()
                currentScore += chancesLeft * context.resources.getInteger(R.integer.value_for_each_chance)
                it.text = currentScore.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateScore", "NumberFormatException: ${e.message}")
            }
        }
    }

    fun updateFinalScore(context: Context, textViewId: String, currentScore: Int) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)
        val textView = (context as? AppCompatActivity)?.findViewById<TextView>(resourceId)

        textView?.let {
            try {
                it.text = currentScore.toString()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                Log.e("updateFinalScore", "NumberFormatException: ${e.message}")
            }
        }
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
            redDifference < difficulty && greenDifference < difficulty && blueDifference < difficulty
        return levelCleared
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
}
