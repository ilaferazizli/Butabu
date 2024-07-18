package com.activity.butabu.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.CustomAlertDialog
import com.activity.butabu.CustomCountDownTimer
import com.activity.butabu.Database
import com.activity.butabu.R
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.nextWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.databinding.ActivityGameBinding
import com.activity.butabu.objects.Rounds
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2
import kotlin.math.roundToInt

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var countDownTime=60
    private var clockTime = (countDownTime * 1000).toLong()
    private var progressTime = (clockTime / 1000).toFloat()
    private var secondLeft=0
    private val onBackPressCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedCustom()
            resetValues()
        }
    }
    private var database=Database()
    private lateinit var customAlertDialog: CustomAlertDialog
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

        customCountDownTimer= object : CustomCountDownTimer(60000, 1000){}
        customAlertDialog = CustomAlertDialog(this, customCountDownTimer)

        database.createSQLdb(this)
        database.readSQLdb(this)
        generateWords()

        setupListeners()
        setupTimer()

        countWord(binding.cancel, binding.cancelCount)
        countWord(binding.next, binding.nextCount)
        countWord(binding.done, binding.doneCount)


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

        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        customCountDownTimer.onTickListener={millisUntilFinished ->
            val second = (millisUntilFinished / 1000.0f).roundToInt()
            if(second!=secondLeft){
                secondLeft=second
            }
            if(progressTime.toInt() ==4*secondLeft){
                changeCountTimeColor(R.color.red)
            }
            syncWithTimer(secondLeft)

        }
        customCountDownTimer.onFinishListener={
            changeCountTimeColor(R.color.green)
            syncWithTimer(0)
            customAlertDialog.showGameOverDialog()
            binding.cancelCount.text=0.toString()
            binding.nextCount.text=0.toString()
            binding.doneCount.text=0.toString()
        }
        binding.countTime.max=progressTime.toInt()
        binding.countTime.progress=progressTime.toInt()
        customCountDownTimer.start()
    }
    private fun setupListeners() {
        binding.cancel.setOnClickListener {
            cancelledWord++

            binding.cancelCount.text = cancelledWord.toString()
        }

        binding.backBack.setOnClickListener {
            finish()
            resetValues()
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
    private fun changeCountTimeColor(color:Int){
        binding.countTime.progressDrawable.setTint(resources.getColor(color))
        binding.timeCircle.setTextColor(resources.getColor(color))
    }
    private fun syncWithTimer(secondLeft:Int){
        binding.countTime.progress=secondLeft
        binding.timeCircle.text=secondLeft.toString()
    }
    private fun countWord(button:View, text: TextView,){
        button.setOnClickListener{
            generateWords()
            when(button.id){
                R.id.cancel -> {
                    cancelledWord++
                    text.text = cancelledWord.toString()
                }
                R.id.next -> {
                    nextWord++
                    text.text = nextWord.toString()
                }
                R.id.done -> {
                    if(!Team1.played){
                        Team1.totalCorrect++
                        binding.team1.text = Team1.totalCorrect.toString()
                    }
                    else{
                        Team2.totalCorrect++
                        binding.team2.text = Team2.totalCorrect.toString()
                    }
                    correctWord++
                    text.text = correctWord.toString()
                }
            }
        }
    }
    private fun resetValues(){
        cancelledWord=0
        nextWord=0
        correctWord=0
        Team1.totalCancelled=0
        Team1.totalCorrect=0
        Team1.totalNext=0
        Team2.totalCancelled=0
        Team2.totalCorrect=0
        Team2.totalNext=0
        Team1.played=false
        Team2.played=false
        Rounds.roundCurrent=1
    }
    private fun generateWords(){
        val words=database.wordList.random()
        binding.mainWord.text=words.word
        binding.prohibited1.text=words.prohibitedWords
        binding.prohibited2.text=words.prohibitedWords
        binding.prohibited3.text=words.prohibitedWords
        binding.prohibited4.text=words.prohibitedWords
        binding.prohibited5.text=words.prohibitedWords
    }

}
