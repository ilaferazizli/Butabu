package com.activity.butabu.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityDifficultyBinding
import com.activity.butabu.dataclasses.Words
import com.activity.butabu.objects.FireStoreRepository

class DifficultyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDifficultyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficulty)
        binding = ActivityDifficultyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.geri.setOnClickListener {
            finish()
        }

        listOf(binding.sade, binding.orta, binding.cetin).forEach { view ->
            view.setOnClickListener {
                button ->
                binding.loading.visibility= View.VISIBLE
                FireStoreRepository().fetchData(
                    onFetchComplete = {
                        binding.loading.visibility= View.INVISIBLE
                        Log.d("MainActivity", "Words fetched successfully")
                        Toast.makeText(this, "${button.tag}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("level", "${button.tag}")
                        startActivity(intent)
                    }
                )

            }
        }
    }
}