package com.example.barega

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class LevelFailedDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.level_failed, container, false)
        val okButton = view.findViewById<Button>(R.id.okButton)
        okButton.setOnClickListener {
            // Handle "OK" button click here
            Utils.setLevelQuestionColor(requireContext())
            Utils.resetChancesLeft(requireContext(), "chancesLeft")
            Utils.resetLevel(requireContext(), "currentLevel")
            Utils.resetScore(requireContext(), "currentScore")
            dismiss() // Close the dialog
        }
        // Prevent the dialog from being canceled when touched outside its window
        dialog?.setCanceledOnTouchOutside(false)
        return view
    }
}