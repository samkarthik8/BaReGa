package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
        // Read JSON from scores.json file in assets folder
        val jsonScores: String = try {
            applicationContext.assets.open("scores.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            // Handle exception (e.g., file not found)
            ""
        }
        // Convert JSON string to List<Score>
        val scoresList = Gson().fromJson<List<Score>>(
            jsonScores,
            object : TypeToken<List<Score>>() {}.type
        )
        // Save the scores to SharedPreferences
        scoresManager.saveScores(scoresList)
        // Retrieve the scores from SharedPreferences
        val topScores = scoresManager.getTopScores()
        // Add headings to the table
        addHeadingsToTable()
        // Add scores to the table
        for (score in topScores) {
            addScoreToTable(score)
        }
    }

    private fun addHeadingsToTable() {
        val headingRow = TableRow(this)
        headingRow.addView(createHeadingTextView("NAME"))
        headingRow.addView(createHeadingTextView("LEVEL"))
        headingRow.addView(createHeadingTextView("SCORE"))
        tableLayout.addView(headingRow)
    }

    private fun createHeadingTextView(text: String): TextView {
        return TextView(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply { weight = 1f }
            this.text = text
            setTextAppearance(R.style.TextAppearance_AppCompat_Medium)
            gravity = Gravity.CENTER
            setPadding(0, 100, 0, 100) // Adjust the values as needed
        }
    }

    private fun addScoreToTable(score: Score) {
        val scoreRow = TableRow(this)
        scoreRow.addView(createScoreTextView(score.playerName))
        scoreRow.addView(createScoreTextView(score.level.toString()))
        scoreRow.addView(createScoreTextView(score.scoreValue.toString()))
        tableLayout.addView(scoreRow)
    }

    private fun createScoreTextView(text: String): TextView {
        return TextView(this).apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            ).apply { weight = 1f }
            this.text = text
            setTextAppearance(R.style.TextAppearance_AppCompat_Body1)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 50) // Adjust the values as needed
        }
    }
    @Suppress("UNUSED_PARAMETER")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
    }
}
