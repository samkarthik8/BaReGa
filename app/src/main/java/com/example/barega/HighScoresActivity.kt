package com.example.barega

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HighScoresActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var scoresManager: ScoresManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)

        scoresManager = ScoresManager(this)

        recyclerView = findViewById(R.id.recyclerViewScores)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
        // Convert JSON string to List<com.example.barega.Score>
        val scoresList = Gson().fromJson<List<Score>>(
            jsonScores,
            object : TypeToken<List<Score>>() {}.type
        )
        // Save the scores to SharedPreferences
        scoresManager.saveScores(scoresList)
        // Retrieve the scores from SharedPreferences
        val topScores = scoresManager.getTopScores()
        // Set up RecyclerView adapter
        recyclerView.adapter = ScoreListAdapter(this, topScores)
    }
    @SuppressWarnings("unused")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
    }
}

