package com.example.barega

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreListAdapter(private val context: Context, private val scores: List<Score>) :
    RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder>() {
    class ScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scoreTextView: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ScoreViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        val currentScore = scores[position]
        // Format the string using the context from the adapter
        val formattedString = context.getString(
            R.string.player_score,
            currentScore.playerName,
            currentScore.level,
            currentScore.scoreValue
        )

        holder.scoreTextView.text = formattedString
    }

    override fun getItemCount(): Int {
        return scores.size
    }
}
