package com.activity.butabu.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityGameOverBinding
import com.activity.butabu.databinding.ActivitySoloGameOverBinding
import com.activity.butabu.objects.GameProperties
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2
import com.activity.butabu.objects.WordCounts
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.objects.WordCounts.nextWord

class SoloGameOverActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoloGameOverBinding
    private val onBackPressCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val intent = Intent(this@SoloGameOverActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            WordCounts.reset()
            }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solo_game_over)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        binding = ActivitySoloGameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            WordCounts.reset()
        }
        binding.playAgain.setOnClickListener {
            val intent = Intent(this, SoloGameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            WordCounts.reset()
        }


        binding.finalCancelled.text= cancelledWord.toString()
        binding.finalNext.text= nextWord.toString()
        binding.finalCorrect.text= correctWord.toString()

    }
}
