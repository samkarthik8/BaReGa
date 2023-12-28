@file:Suppress("unused", "RedundantSuppression")

package com.example.barega

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private val sharedPreferencesSettings = "SettingsPrefsFile"
    private val defaultPlayerName = "Karthik"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun onChangePlayerNameButtonClick(view: View) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        // Retrieve the existing player name and set it as the default text
        val currentName = getCurrentPlayerNameFromPrefs()
        if (currentName.isEmpty()) {
            updatePlayerNameInPrefs(defaultPlayerName)
        }
        input.setText(currentName)
        // Set the maximum length of the input to 10 characters
        val maxLength = 10
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
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
                updatePlayerNameInPrefs(newPlayerName)
                alertDialog.dismiss() // Dismiss the dialog when conditions are met
            } else {
                // Show a toast or any other feedback indicating the minimum length requirement
                // In a real application, consider using a Snackbar or a custom Toast for a better user experience
                Toast.makeText(
                    this,
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
    // Function to retrieve the current player name from SharedPreferences
    private fun getCurrentPlayerNameFromPrefs(): String {
        val prefs: SharedPreferences =
            getSharedPreferences(sharedPreferencesSettings, Context.MODE_PRIVATE)
        return prefs.getString("currentPlayerName", "") ?: ""
    }
    // Function to update the player name in SharedPreferences
    private fun updatePlayerNameInPrefs(newPlayerName: String) {
        val prefs: SharedPreferences =
            getSharedPreferences(sharedPreferencesSettings, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString("currentPlayerName", newPlayerName)
        editor.apply()
    }
    @Suppress("unused")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish() // Finish the activity immediately after starting TitleActivity
    }
}
