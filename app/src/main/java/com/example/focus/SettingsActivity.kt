package com.example.focus

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.focus.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("focus_prefs", MODE_PRIVATE)
        binding.cbInstagram.isChecked = prefs.getBoolean("block_instagram", false)
        binding.cbInstagramReels.isChecked = prefs.getBoolean("block_instagram_reels", false)
        binding.cbYouTube.isChecked = prefs.getBoolean("block_youtube", false)
        binding.cbYouTubeShorts.isChecked = prefs.getBoolean("block_youtube_shorts", false)

        binding.btnSave.setOnClickListener {
            val active = prefs.getBoolean("focus_active", false)
            if (active) {
                val pin = prefs.getString("focus_pin", null)
                if (pin == null) {
                    showMessage("No PIN set. Cannot modify while Focus Mode active.")
                    return@setOnClickListener
                }
                val input = EditText(this)
                input.hint = "Enter PIN"
                AlertDialog.Builder(this)
                    .setTitle("Unlock to change settings")
                    .setView(input)
                    .setPositiveButton("OK") { _, _ ->
                        val entered = input.text.toString()
                        if (entered == pin) saveSettings(prefs) else showMessage("Incorrect PIN")
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            } else {
                saveSettings(prefs)
            }
        }

        binding.btnSetPin.setOnClickListener {
            val input = EditText(this)
            input.hint = "Enter new PIN"
            AlertDialog.Builder(this)
                .setTitle("Set PIN")
                .setView(input)
                .setPositiveButton("Save") { _, _ ->
                    val newPin = input.text.toString()
                    if (newPin.length >= 4) { prefs.edit().putString("focus_pin", newPin).apply(); showMessage("PIN saved") } else showMessage("PIN must be 4 digits") }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun saveSettings(prefs: android.content.SharedPreferences) {
        prefs.edit()
            .putBoolean("block_instagram", binding.cbInstagram.isChecked)
            .putBoolean("block_instagram_reels", binding.cbInstagramReels.isChecked)
            .putBoolean("block_youtube", binding.cbYouTube.isChecked)
            .putBoolean("block_youtube_shorts", binding.cbYouTubeShorts.isChecked)
            .apply()
        showMessage("Settings saved")
    }

    private fun showMessage(msg: String) {
        AlertDialog.Builder(this).setMessage(msg).setPositiveButton("OK", null).show()
    }
}
