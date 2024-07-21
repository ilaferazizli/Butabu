package com.activity.butabu.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityTeamsBinding
import com.activity.butabu.objects.GameProperties
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2

class TeamsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_teams)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityTeamsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadSettings()
        binding.bitdi.setOnClickListener {
            saveSettings()
            finish()
        }
    }
    private fun saveSettings(){
        if(binding.team1Name.text.toString().isNotEmpty() && binding.team2Name.text.toString().isNotEmpty()) {
            Team1.name = binding.team1Name.text.toString()
            Team2.name = binding.team2Name.text.toString()
        }
        GameProperties.roundTotal=binding.roundCount.text.toString().toInt()
        Toast.makeText(this, "Parametrler deyişdirildi", Toast.LENGTH_SHORT).show()
    }
    private fun loadSettings(){
        binding.team1Name.setHint(Team1.name)
        binding.team2Name.setHint(Team2.name)
        binding.roundCount.setText(GameProperties.roundTotal.toString())
    }
}