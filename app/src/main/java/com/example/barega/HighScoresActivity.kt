@file:Suppress("unused", "RedundantSuppression")

package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HighScoresActivity : AppCompatActivity() {
    private lateinit var tableLayout: TableLayout
    private lateinit var scoresManager: ScoresManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)
        scoresManager = ScoresManager(this)
        tableLayout = findViewById(R.id.tableLayoutScores)
        updateScoresList()
    }

    private fun updateScoresList() {
        // Retrieve the scores from ScoresManager
        val topScores = scoresManager.getTopScores()
        // Add headings to the table
        addHeadingsToTable()
        // Add scores to the table
        for (player in topScores) {
            addScoreToTable(player)
        }
    }

    private fun addHeadingsToTable() {
        val headingRow = TableRow(this)
        headingRow.addView(createHeadingTextView(R.string.high_score_column1))
        headingRow.addView(createHeadingTextView(R.string.high_score_column2))
        headingRow.addView(createHeadingTextView(R.string.high_score_column3))
        tableLayout.addView(headingRow)
    }
    override fun onResume() {
        super.onResume()
        Utils.setSelectedThemeColors(this)
    }
    private fun createHeadingTextView(textResId: Int): TextView {
        return TextView(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply { weight = 1f }
            this.text = getString(textResId)
            setTextAppearance(R.style.style_score_header)
            gravity = Gravity.CENTER
            setPadding(0, 100, 0, 100) // Adjust the values as needed
        }
    }

    private fun addScoreToTable(player: ScoresManager.Player) {
        val scoreRow = TableRow(this)
        scoreRow.addView(createScoreTextView(player.playerName))
        scoreRow.addView(createScoreTextView(player.level.toString()))
        scoreRow.addView(createScoreTextView(player.scoreValue.toString()))
        tableLayout.addView(scoreRow)
    }

    private fun createScoreTextView(text: String): TextView {
        return TextView(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply { weight = 1f }
            this.text = text
            setTextAppearance(R.style.style_score_row)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 50) // Adjust the values as needed
        }
    }
    fun onBackButtonClick(button: View) {
        assert(button.id == R.id.backButton)
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish() // Finish the activity immediately after starting TitleActivity
    }
}
