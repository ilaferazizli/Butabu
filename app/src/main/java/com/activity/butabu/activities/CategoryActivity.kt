package com.activity.butabu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityCategoryBinding
import com.activity.butabu.objects.FireStoreRepository

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(R.layout.activity_difficulty)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addWord.setOnClickListener{
            val intent=Intent(this, DifficultyActivity::class.java)
            intent.putExtra("action","addWord")
            startActivity(intent)
        }
        binding.geri.setOnClickListener{
            finish()
        }
        listOf(binding.thing, binding.place, binding.food,binding.animal).forEach { view ->
            view.setOnClickListener {
                    button ->
                binding.loading.visibility= View.VISIBLE
                FireStoreRepository().fetchData(
                    searchWith = "category",
                    searchValue = "${button.tag}",
                    onFetchComplete = {
                        binding.loading.visibility= View.INVISIBLE
                        Toast.makeText(this, "${button.tag}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, GameActivity::class.java)
                        startActivity(intent)
                    }
                )

            }
        }




    }
}