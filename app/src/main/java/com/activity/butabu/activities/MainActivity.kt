package com.activity.butabu.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityMainBinding
import com.activity.butabu.objects.FireStoreRepository
import com.activity.butabu.objects.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.play.setOnClickListener {
            val intent = Intent(this, DifficultyActivity::class.java)
            intent.putExtra("action","play")
            startActivity(intent)
        }
        binding.soloGame.setOnClickListener {
            binding.loadingSolo.visibility= View.VISIBLE
            FireStoreRepository().fetchDataForSolo(
                onFetchComplete = {
                    binding.loadingSolo.visibility= View.INVISIBLE
                    val intent = Intent(this, SoloGameActivity::class.java)
                    startActivity(intent)
                }
            )
        }
        binding.category.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
        binding.setting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.info.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
        binding.instagram.setOnClickListener {
            val instagramProfileUrl = "https://www.instagram.com/dadashoff_707?igsh=MWtsdW91bGRrNWR4eA%3D%3D&utm_source=qr"

            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(instagramProfileUrl)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }
    }
}
