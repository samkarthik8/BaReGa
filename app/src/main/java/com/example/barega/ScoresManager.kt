package com.example.barega

import android.content.Context
import android.content.SharedPreferences

class ScoresManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("high_scores", Context.MODE_PRIVATE)

    data class Player(
        val playerName: String,
        val level: Int,
        val scoreValue: Int
    )

    init {
        // Initialize with hardcoded values initially
        val initialScores = listOf(
            Player("Player1", 1, 1),
            Player("Player2", 1, 2),
            Player("Player3", 1, 3),
            Player("Player4", 1, 4),
            Player("Player5878", 1, 5),
            Player("Player6", 1, 6),
            Player("Player7", 1, 7),
            Player("Player8", 1, 88785),
            Player("Player9", 1, 9),
            Player("Player10", 1, 10)
        )
        saveScores(initialScores)
    }

    private fun saveScores(scores: List<Player>) {
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
}
