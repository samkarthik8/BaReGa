package com.example.barega

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.File

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    fun onChangePlayerNameButtonClick(view: View) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val input = EditText(this)
        // Retrieve the existing player name and set it as the default text
        val currentName = getCurrentPlayerNameFromJSON()
        input.setText(currentName)
        // Set the maximum length of the input to 10 characters
        val maxLength = 10
        input.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
        // Set up the AlertDialog
        alertDialogBuilder.setTitle("Change Player Name")
        alertDialogBuilder.setMessage("Enter your new player name:")
        alertDialogBuilder.setView(input)
        // Set up the positive button action
        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            val newPlayerName = input.text.toString()
            updatePlayerName(this, newPlayerName)
        }
        // Set up the negative button action (optional)
        alertDialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            // Do nothing or handle cancellation
        }

        alertDialogBuilder.show()
    }
    // Function to retrieve the current player name from the settings in the assets folder
    private fun getCurrentPlayerNameFromJSON(): String {
        try {
            val jsonSettings: String = applicationContext.assets.open("settings.json")
                .bufferedReader()
                .use { it.readText() }
            // Parse the JSON string
            val jsonArray = JSONArray(jsonSettings)
            // Check if there is at least one item in the array
            if (jsonArray.length() > 0) {
                // Get the first item in the array
                val firstItem = jsonArray.getJSONObject(0)
                // Extract the "currentPlayerName" field
                return firstItem.optString("currentPlayerName", "")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun updatePlayerName(context: Context, newPlayerName: String) {
        try {
            val settingsFileName = "settings.json"
            val settingsFile = File(context.filesDir, settingsFileName)
            // Ensure the directory structure exists
            settingsFile.parentFile?.mkdirs()
            // Check if the file exists, and create it if not
            if (!settingsFile.exists()) {
                settingsFile.createNewFile()
                // Initialize the file with an empty JSON array
                settingsFile.writeText("[]")
            }
            // Read the existing JSON data
            val jsonString = settingsFile.readText()
            val jsonArray = JSONArray(jsonString)
            // Update the player name in the first item of the array
            if (jsonArray.length() > 0) {
                val firstItem = jsonArray.getJSONObject(0)
                firstItem.put("currentPlayerName", newPlayerName)
                // Save the updated JSON array back to the file
                settingsFile.writeText(jsonArray.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exceptions (e.g., file creation error, JSON parsing error)
        }
    }
    @Suppress("unused")
    fun onBackButtonClick(view: View) {
        // Start TitleActivity
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)
        finish() // Finish the activity immediately after starting TitleActivity
    }
}
