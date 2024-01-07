package com.example.barega

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment

class LevelPassedDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.level_passed, container, false)

        val okButton = view.findViewById<Button>(R.id.okButton)

        okButton.setOnClickListener {
            // Handle "OK" button click here

            dismiss() // Close the dialog
        }
        // Prevent the dialog from being canceled when touched outside its window
        dialog?.setCanceledOnTouchOutside(false)
        return view
    }
}
