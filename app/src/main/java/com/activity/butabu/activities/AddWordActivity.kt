package com.activity.butabu.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityAddWordBinding
import com.activity.butabu.dataclasses.Words
import com.activity.butabu.objects.FireStoreRepository

class AddWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_word)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityAddWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBack.setOnClickListener {
            finish()
        }
        binding.saveSettings.setOnClickListener {
            addWordToFireStore()
            Toast.makeText(this, "Əlavə edildi", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
    private fun addWordToFireStore() {
        FireStoreRepository().writeData(
            Words(
                language = "azerbaycan",
                category = binding.addCategory.text.toString(),
                mainWord = binding.addMainWord.text.toString(),
                difficulty = intent.getStringExtra("addWordlevel")?:"sade",
                prohibitedWords = listOf(
                    binding.addProhibited1.text.toString(),
                    binding.addProhibited2.text.toString(),
                    binding.addProhibited3.text.toString(),
                    binding.addProhibited4.text.toString()
                )
            )
        )
    }
}