@file:Suppress("unused", "RedundantSuppression")

package com.example.barega

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private val defaultPlayerName = "Karthik"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun onResetHighScoresClick(view: View) {
        val scoresManager = ScoresManager(view.context)
        scoresManager.resetHighScores(view.context)
    }

    fun onChangePlayerNameButtonClick(view: View) {
        val alertDialogBuilder = AlertDialog.Builder(view.context)
        val input = EditText(view.context)
        // Retrieve the existing player name and set it as the default text
        val currentName = Utils.getCurrentPlayerNameFromPrefs(view.context)
        if (currentName.isEmpty()) {
            Utils.updatePlayerNameInPrefs(view.context, defaultPlayerName)
        }
        input.setText(currentName)
        // Set the maximum length of the input to 10 characters
        val maxLength = 10
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        // Flag to track whether the name is changed
        var nameChanged = false
        // Set up the AlertDialog
        alertDialogBuilder.setTitle("Change Player Name")
        alertDialogBuilder.setMessage("Enter your new player name:")
        alertDialogBuilder.setView(input)
        // Set up the positive button action with custom listener
        alertDialogBuilder.setPositiveButton("OK", null) // Set a null listener initially
        val alertDialog = alertDialogBuilder.create()
        // Show the dialog
        alertDialog.show()
        // Override the positive button's onClick listener
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            // Check if the new player name meets the minimum length requirement
            val newPlayerName = input.text.toString()
            if (newPlayerName.length >= 3) {
                if (currentName != newPlayerName) {
                    // Name has changed
                    Utils.updatePlayerNameInPrefs(view.context, newPlayerName)
                    nameChanged = true
                }
                alertDialog.dismiss() // Dismiss the dialog when conditions are met
                // Show a Toast indicating the player name change or not
                val toastMessage = if (nameChanged) {
                    "Player name changed to: $newPlayerName"
                } else {
                    "Player name remains the same: $currentName"
                }
                Toast.makeText(view.context, toastMessage, Toast.LENGTH_SHORT).show()
            } else {
                // Show a toast or any other feedback indicating the minimum length requirement
                // In a real application, consider using a Snackbar or a custom Toast for a better user experience
                Toast.makeText(
                    view.context,
                    "Player name must be at least 3 characters long",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        // Set up the negative button action (optional)
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            // Do nothing or handle cancellation
        }
    }

    fun onToggleThemeClick(view: View) {
        val defaultThemeName = getString(R.string.toggle_theme_light_string)
        var selectedThemeName = Utils.getSelectedThemeNameFromPrefs(view.context)
        if (selectedThemeName.isEmpty()) {
            Utils.updateSelectedThemeInPrefs(view.context, defaultThemeName)
        }
        // Check if the selected theme contains "Dark"
        if (selectedThemeName.contains("Dark", ignoreCase = true)) {
            // If it contains "Dark", set it to "toggle_theme_light_string"
            Utils.updateSelectedThemeInPrefs(
                view.context,
                getString(R.string.toggle_theme_light_string)
            )
            selectedThemeName = getString(R.string.toggle_theme_light_string)
        } else if (selectedThemeName.contains("Light", ignoreCase = true)) {
            // If it contains "Light", set it to "toggle_theme_dark_string"
            Utils.updateSelectedThemeInPrefs(
                view.context,
                getString(R.string.toggle_theme_dark_string)
            )
            selectedThemeName = getString(R.string.toggle_theme_dark_string)
        }
// Update the text attribute of the toggleThemeButton based on the selected theme
        val toggleThemeButton: Button = view.findViewById(R.id.toggleThemeButton)
        toggleThemeButton.text = if (selectedThemeName.contains("Dark", ignoreCase = true)) {
            getString(R.string.toggle_theme_light_string)
        } else {
            getString(R.string.toggle_theme_dark_string)
        }
        // Show a Toast indicating the theme changed
        val toastMessage =
            "Theme changed to: $selectedThemeName"
        Toast.makeText(view.context, toastMessage, Toast.LENGTH_SHORT).show()
    }
    @Suppress("unused")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish() // Finish the activity immediately after starting TitleActivity
    }
}
