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
            Player("Aarav", 5, 1200),
            Player("Karthik", 4, 1000),
            Player("Arjun", 3, 800),
            Player("Ishaan", 2, 600),
            Player("Neha", 1, 400),
            Player("Kavya", 1, 200),
            Player("Rohan", 1, 1500),
            Player("Priya", 1, 500),
            Player("Aditya", 1, 50),
            Player("Rahul", 1, 25)
        )
        saveScores(initialScores)
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
}
