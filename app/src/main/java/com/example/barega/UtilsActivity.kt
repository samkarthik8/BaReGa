package com.example.barega

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
}
