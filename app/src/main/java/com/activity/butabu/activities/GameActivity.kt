package com.activity.butabu.activities

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.CustomCountDownTimer
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivityGameBinding
import com.activity.butabu.databinding.AlertGameOverBinding
import kotlin.math.roundToInt


class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private var countDownTime=60
    private var clockTime = (countDownTime * 1000).toLong()
    private var progressTime = (clockTime / 1000).toFloat()

    private val onBackPressCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedCustom()
        }
    }
    private lateinit var customCountDownTimer: CustomCountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customCountDownTimer= object : CustomCountDownTimer(5000, 1000){}
        setupListeners()
        setupTimer()


    }

    override fun onPause() {
        super.onPause()
        customCountDownTimer.pauseTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        customCountDownTimer.destroy()
    }
    override fun onResume() {
        super.onResume()
        customCountDownTimer.resumeTimer()
    }
    private fun onBackPressedCustom() {
        customCountDownTimer.destroy()
        finish()
    }
    private fun setupTimer(){
        var secondLeft=0
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        customCountDownTimer.onTickListener={millisUntilFinished ->
            val second = (millisUntilFinished / 1000.0f).roundToInt()
            binding.countTime.progress=second
            binding.timeCircle.text=second.toString()
            if(second!=secondLeft){
                secondLeft=second
            }
        }
        customCountDownTimer.onFinishListener={
            showAlert()
        }
        binding.countTime.max=progressTime.toInt()
        binding.countTime.progress=progressTime.toInt()
        customCountDownTimer.start()
    }
    private fun setupListeners() {
        binding.backBack.setOnClickListener {
            finish()
        }
        binding.pause.setOnClickListener {
            customCountDownTimer.pauseTimer()
            binding.play.visibility= View.VISIBLE
            binding.pause.visibility=View.INVISIBLE
        }
        binding.play.setOnClickListener {
            customCountDownTimer.resumeTimer()
            binding.play.visibility= View.INVISIBLE
            binding.pause.visibility=View.VISIBLE
        }
    }
    private fun showAlert() {
        var alertDialog: AlertDialog? = null
        val builder = AlertDialog.Builder(this)
        val view = AlertGameOverBinding.inflate(layoutInflater)
        builder.setView(view.root)

        view.basla.setOnClickListener {
            alertDialog?.dismiss()
            customCountDownTimer.restart()
        }
        alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawableResource(R.drawable.bg_result_stroke)
        alertDialog.show()
    }

}
