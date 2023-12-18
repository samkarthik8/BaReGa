package com.example.barega

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScoresManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("high_scores", Context.MODE_PRIVATE)

    fun saveScores(scores: List<Score>) {
        val json = Gson().toJson(scores)
        sharedPreferences.edit().putString("scores", json).apply()
    }

    fun getTopScores(): List<Score> {
        val json = sharedPreferences.getString("scores", null)
        return if (json != null) {
            val typeToken = object : TypeToken<List<Score>>() {}.type
            val scores = Gson().fromJson<List<Score>>(json, typeToken)
            scores.sortedByDescending { it.scoreValue }
        } else {
            emptyList()
        }
    }
}
