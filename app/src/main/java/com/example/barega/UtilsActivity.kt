package com.example.barega

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

object Utils {
    fun setTextViewBackgroundColor(
        context: Context,
        textViewId: String,
        red: Int,
        green: Int,
        blue: Int
    ) {
        val resourceId = context.resources.getIdentifier(textViewId, "id", context.packageName)

        if (resourceId != 0) {
            val textView = (context as AppCompatActivity).findViewById<TextView>(resourceId)
            val backgroundColor = Color.rgb(red, green, blue)
            textView.setBackgroundColor(backgroundColor)
        }
    }
}
