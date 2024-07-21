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
        loadSettings()
        binding.saveSettings.setOnClickListener {
            saveSettings()
            finish()
        }

        binding.goTeams.setOnClickListener {
            saveSettings()
            val intent = Intent(this, TeamsActivity::class.java)
            startActivity(intent)
        }

        timeDropDown()
        langDropDown()

    }

    private fun saveSettings() {
        GameProperties.time = binding.timeEditText.text.toString().toInt()
        GameProperties.passCount = binding.passCount.text.toString().toInt()
        GameProperties.warningSound= binding.warningSound.isChecked
        GameProperties.language=binding.languageDropdown.text.toString()
        Toast.makeText(this, "Parametrler deyişdirildi", Toast.LENGTH_SHORT).show()
    }
    private fun loadSettings() {
        binding.timeEditText.setText(GameProperties.time.toString())
        binding.passCount.setText(GameProperties.passCount.toString())
        binding.warningSound.isChecked=GameProperties.warningSound
    }
    private fun timeDropDown() {
        val times = arrayOf("10", "30", "60", "90")
        val adapter = ArrayAdapter(this, R.layout.drop_down_layout_time, times)
        binding.timeEditText.setAdapter(adapter)
    }
    private fun langDropDown(){
        val languages = arrayOf("Azərbaycan dili", "English", "Türkçe")
        val adapter = ArrayAdapter(this, R.layout.drop_down_layout_lang, languages)
        binding.languageDropdown.setAdapter(adapter)
    }
}