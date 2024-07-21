package com.activity.butabu.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivitySettingsBinding
import com.activity.butabu.objects.GameProperties

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveSettings.setOnClickListener {
            saveSettings()
            finish()
        }

        val languages = arrayOf("Azərbaycan dili", "English", "Türkçe")
        val adapter = ArrayAdapter(this, R.layout.drop_down_layout, languages)
        binding.languageDropdown.setAdapter(adapter)

        binding.languageDropdown.setOnItemClickListener { parent, view, position, id ->
            val selectedLanguage = languages[position]
            GameProperties.language=selectedLanguage
            Toast.makeText(this, "Selected language: $selectedLanguage", Toast.LENGTH_SHORT).show()
        }
        binding.goTeams.setOnClickListener {
            saveSettings()
            val intent = Intent(this, TeamsActivity::class.java)
            startActivity(intent)
        }
    }
    private fun timeStringToSeconds(timeString: String): Int {
        val parts = timeString.split(":")
        if (parts.size == 2) {
            val minutes = parts[0].toIntOrNull() ?: 0
            val seconds = parts[1].toIntOrNull() ?: 0
            return minutes * 60 + seconds
        } else {
            return 0
        }
    }
    private fun saveSettings() {
        GameProperties.time = timeStringToSeconds(binding.time.text.toString())
        GameProperties.passCount = binding.passCount.text.toString().toInt()
        GameProperties.warningSound= binding.warningSound.isActivated
        Toast.makeText(this, "Parametrler deyişdirildi", Toast.LENGTH_SHORT).show()
    }
}