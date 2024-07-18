package com.activity.butabu.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityGameOverBinding
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2

class GameOverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameOverBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.komanda1.text=Team1.name
        binding.komanda2.text= Team2.name
        binding.komanda1Exit.text=Team1.totalCancelled.toString()
        binding.komanda2Exit.text=Team2.totalCancelled.toString()
        binding.komanda1Next.text=Team1.totalNext.toString()
        binding.komanda2Next.text=Team2.totalNext.toString()
        binding.komanda1Okey.text=Team1.totalCorrect.toString()
        binding.komanda2Okey.text=Team2.totalCorrect.toString()
        if (Team1.totalCorrect>Team2.totalCorrect)
            binding.teamWon.text=Team1.name
        else if(Team1.totalCorrect==Team2.totalCorrect)
            "Berabere".also { binding.teamWon.text = it }
        else
            binding.teamWon.text=Team2.name
    }
}