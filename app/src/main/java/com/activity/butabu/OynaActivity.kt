package com.activity.butabu

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.activity.butabu.databinding.ActivityOynaBinding

class OynaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOynaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oyna)
        binding = ActivityOynaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.geri.setOnClickListener {
            finish()
        }
        listOf(binding.sade, binding.orta, binding.cetin).forEach { view ->
            view.setOnClickListener {
                Toast.makeText(this, "${it.tag}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityOyun::class.java)
                intent.putExtra("level", "${binding.sade.tag}")
                startActivity(intent)
            }
        }
    }
}