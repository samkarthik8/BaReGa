package com.example.barega

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class ScoresManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("high_scores", Context.MODE_PRIVATE)

    data class Player(
        val playerName: String,
        val level: Int,
        val scoreValue: Int
    )

    init {
        // Check if scores are not already initialized in preferences
        if (sharedPreferences.getStringSet("scores", null) == null) {
            // Initialize with hardcoded values if scores are empty
            val initialScores = listOf(
                Player("Player1", 1, 1),
                Player("Player2", 1, 2),
                Player("Player3", 1, 3),
                Player("Player4", 1, 4),
                Player("Player5", 1, 5),
                Player("Player6", 1, 6),
                Player("Player7", 1, 7),
                Player("Player8", 1, 8),
                Player("Player9", 1, 9),
                Player("Player10", 1, 10)
            )
            saveScores(initialScores)
        }
    }

    fun saveScores(scores: List<Player>) {
        // Convert the list of Player objects to a Set of Strings
        val scoresSet = scores.map { "${it.playerName},${it.level},${it.scoreValue}" }.toSet()
        sharedPreferences.edit().putStringSet("scores", scoresSet).apply()
    }

    fun getTopScores(): List<Player> {
        val scoresSet = sharedPreferences.getStringSet("scores", null)
        return if (scoresSet != null) {
            // Convert the Set of Strings back to a List of Player objects
            scoresSet.map {
                val parts = it.split(",")
                Player(parts[0], parts[1].toInt(), parts[2].toInt())
            }.sortedByDescending { it.scoreValue }
        } else {
            emptyList()
        }
    }

    fun isHigherScore(newScore: Player): Boolean {
        val topScores = getTopScores()
        return topScores.any { newScore.scoreValue > it.scoreValue }
    }

    fun resetHighScores(context: Context) {
        sharedPreferences.edit().remove("scores").apply()
        // Display a Toast notification after resetting high scores
        Toast.makeText(context, "High scores reset", Toast.LENGTH_SHORT).show()
    }
}
