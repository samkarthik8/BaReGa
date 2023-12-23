package com.example.barega

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

class IntractableDrawingTextView : AppCompatTextView {
    private var dotX: Float = 0f
    private var dotY: Float = 0f
    private val dotRadius = 10f
    private val dotPaint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        dotPaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the dot on the canvas
        canvas.drawCircle(dotX, dotY, dotRadius, dotPaint)
    }
    // Create a map to store color values based on resource IDs
    private val colorMap: Map<String, Int> = mapOf(
        "com.example.barega:id/redBar" to Color.GREEN,
        "com.example.barega:id/blueBar" to Color.RED,
        "com.example.barega:id/greenBar" to Color.BLUE,
    )

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                // Update the dot position when the user touches or moves
                dotX = event.x
                dotY = event.y
                // Get the ID of the clicked view
                val resourceId = resources.getResourceName(id)
                // Get the color based on the resourceId
                val dotColor = colorMap[resourceId] ?: Color.BLACK
                // Set dotPaint.color to the selected color
                dotPaint.color = dotColor
                // Invalidate the view to trigger a redraw
                invalidate()
                // Call your custom click handling logic
                handleCustomClick()
                return true
            }

            else -> return super.onTouchEvent(event)
        }
    }

    private fun findFragmentManager(context: Context): FragmentManager? {
        var currentContext = context
        while (currentContext is ContextWrapper) {
            if (currentContext is FragmentActivity) {
                return currentContext.supportFragmentManager
            }
            currentContext = currentContext.baseContext
        }
        return null
    }

    private fun handleCustomClick() {
        super.performClick()
        // Handle the click event here
        // You can also call super.performClick() if needed
        // Get the ID of the clicked view
        val resourceId = resources.getResourceName(id)
        var red = 0
        var green = 0
        var blue = 0
        var (currentRed, currentGreen, currentBlue) =
            Utils.getCurrentRGBBackgroundColor(context, "answerColorSection")
        Log.d("Resource Id", "resourceId clicked is $resourceId")
        // Get the click position
        val clickY = dotY
        val maxY = this.height
        Log.d("Max Y Value", "Max Y: $maxY")
        // Log or use the click coordinates as needed
        Log.d("ClickPosition", " Y: $clickY")
        if (resourceId.contains("blue")) {
            // Calculate RGB values based on the Y coordinate
            blue = ((maxY - clickY) / maxY * 255).toInt()
            currentBlue = blue
        }
        if (resourceId.contains("red")) {
            // Calculate RGB values based on the Y coordinate
            red = ((maxY - clickY) / maxY * 255).toInt()
            currentRed = red
        }
        if (resourceId.contains("green")) {
            // Calculate RGB values based on the Y coordinate
            green = ((maxY - clickY) / maxY * 255).toInt()
            currentGreen = green
        }
        // Set the background color based on RGB values
        setBackgroundColor(Color.rgb(red, green, blue))
        // Set the background color based on the Y coordinate
//        Utils.setAnswerColor(context, "answerColorSection", red, green, blue)
        Utils.setCurrentRGBColors(
            context,
            "answerColorSection",
            currentRed,
            currentGreen,
            currentBlue
        )
        val answerTextRed = 255 - currentRed
        val answerTextGreen = 255 - currentGreen
        val answerTextBlue = 255 - currentBlue
        Utils.setTextViewTextColor(
            context,
            "answerColorSection",
            answerTextRed,
            answerTextGreen,
            answerTextBlue
        )
        Utils.updateChancesLeft(
            context,
            "chancesLeft",
        )
        Utils.setTextViewTextColor(
            context,
            "answerColorSection",
            answerTextRed,
            answerTextGreen,
            answerTextBlue
        )
        val levelCleared: Boolean = Utils.checkLevelCleared(context, 50)
// ...

        if (levelCleared) {
            val fragmentManager = findFragmentManager(context)
            if (fragmentManager != null) {
                val levelPassedDialog = LevelPassedDialogFragment()
                levelPassedDialog.show(fragmentManager, "levelPassedDialog")

                // Use a Handler to dismiss the dialog after a delay (1 second)
                Utils.resetChancesLeft(context, "chancesLeft")
                Utils.updateLevel(context, "currentLevel")

                Handler(Looper.getMainLooper()).postDelayed({
                    levelPassedDialog.dismissAllowingStateLoss()
                }, 1000)
            }
        }

    }
}