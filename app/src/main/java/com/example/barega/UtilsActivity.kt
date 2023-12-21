package com.example.barega

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

object Utils {
    @SuppressLint("DiscouragedApi")
    fun setTextViewBackgroundColor(
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
    @SuppressLint("DiscouragedApi")
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

    fun getRandomRGBValues(): Int {
        return Random.nextInt(256) // Generates a random number between 0 (inclusive) and 256 (exclusive)
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
