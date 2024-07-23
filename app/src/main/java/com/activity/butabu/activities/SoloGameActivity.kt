package com.activity.butabu.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.activity.butabu.CustomCountDownTimer
import com.activity.butabu.CustomMainWord
import com.activity.butabu.QuitWarning
import com.activity.butabu.R
import com.activity.butabu.databinding.ActivitySoloGameBinding
import com.activity.butabu.objects.FireStoreRepository
import com.activity.butabu.objects.GameProperties
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.objects.WordCounts.nextWord
import kotlin.math.roundToInt

class SoloGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoloGameBinding
    private val onBackPressCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressedCustom()
        }
    }
    private var customMainWord=CustomMainWord()
    private var countDownTime=GameProperties.time
    private var clockTime = (countDownTime * 1000).toLong()
    private var progressTime = (clockTime / 1000).toFloat()
    private var secondLeft=0
    private var wordList = FireStoreRepository.wordListSolo
    private var word=wordList.random()
    private lateinit var quitWarning: QuitWarning
    private lateinit var customCountDownTimer: CustomCountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solo_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivitySoloGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextCountSolo.text = GameProperties.passCount.toString()
        customCountDownTimer = object : CustomCountDownTimer(clockTime, 1000) {}
        quitWarning = QuitWarning(this, customCountDownTimer)

        generateWords()
        setupListeners()
        setupTimer()
        countWord(binding.cancelSolo, binding.cancelCountSolo)
        countWord(binding.nextSolo, binding.nextCountSolo)
        countWord(binding.doneSolo, binding.doneCountSolo)

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
        quitWarning.showGameOverDialog()
        changeTimerColor(R.color.green)
    }
    private fun setupTimer(){
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
        customCountDownTimer.onTickListener={millisUntilFinished ->
            val second = (millisUntilFinished / 1000.0f).roundToInt()
            if(second!=secondLeft){
                secondLeft=second
            }
            if(progressTime.toInt() >=4*secondLeft){
                if(GameProperties.warningSound){
                    val mediaPlayer= MediaPlayer.create(this, R.raw.bell_sound)
                    mediaPlayer.start()
                }
                changeTimerColor(R.color.red)
            }
            syncWithTimer(secondLeft)

        }
        customCountDownTimer.onFinishListener={
            changeTimerColor(R.color.green)
            syncWithTimer(0)
            binding.cancelCountSolo.text=0.toString()
            binding.nextCountSolo.text=0.toString()
            binding.doneCountSolo.text=0.toString()
            val intent = Intent(this,SoloGameOverActivity::class.java)
            startActivity(intent)
        }
        binding.countTime.max=progressTime.toInt()
        binding.countTime.progress=progressTime.toInt()
        customCountDownTimer.start()
    }
    private fun changeTimerColor(color:Int){
        binding.countTime.progressDrawable.setTint(resources.getColor(color))
        binding.timeCircleSolo.setTextColor(resources.getColor(color))
    }
    private fun syncWithTimer(secondLeft:Int){
        binding.countTime.progress=secondLeft
        binding.timeCircleSolo.text=secondLeft.toString()
    }
    private fun countWord(button: View, text: TextView,){
        button.setOnClickListener{

            when(button.id){
                R.id.cancel_solo -> {
                    cancelledWord++
                    text.text = cancelledWord.toString()
                    generateWords()
                    slideWords()
                }
                R.id.next_solo -> {
                    nextWord++
                    if(nextWord ==GameProperties.passCount){
                        passLimitReached(binding)
                    }
                    "${GameProperties.passCount - nextWord}".also { text.text = it }
                    generateWords()
                    slideWords()
                }
                R.id.done_solo -> {
                    if (checkMainWord()){
                        correctWord++
                        text.text = correctWord.toString()
                        generateWords()
                        slideWords()
                        customMainWord.changeEditTextsColor(R.drawable.bg_solo_word_correct)
                    }
                    else {
                        customMainWord.changeEditTextsColor(R.drawable.bg_solo_word_wrong)
                    }
                }
            }
        }
    }
    private fun setupListeners() {
        binding.cancelSolo.setOnClickListener {
            cancelledWord++

            binding.cancelCountSolo.text = cancelledWord.toString()
        }

        binding.backSolo.setOnClickListener {
            quitWarning.showGameOverDialog()
        }
        binding.pauseSolo.setOnClickListener {
            customCountDownTimer.pauseTimer()
            binding.play.visibility= View.VISIBLE
            binding.pauseSolo.visibility=View.INVISIBLE
        }
        binding.play.setOnClickListener {
            customCountDownTimer.resumeTimer()
            binding.play.visibility= View.INVISIBLE
            binding.pauseSolo.visibility=View.VISIBLE
        }
        binding.hint.setOnClickListener {
            customMainWord.giveHint(word.mainWord)
        }
    }
    private fun generateWords(){
        customMainWord.clearEditTexts(binding)
        if(wordList.isEmpty()){
            wordList=FireStoreRepository.wordListSolo
        }
        word= wordList.random()
        customMainWord.flexibleTextViews(word.mainWord, this,binding)
        wordList.remove(word)
        binding.spoiler1.text=word.spoilers[0]
        binding.spoiler2.text=word.spoilers[1]
        binding.spoiler3.text=word.spoilers[2]
        binding.spoiler4.text=word.spoilers[3]
        binding.spoiler5.text=word.spoilers[4]
        binding.spoiler6.text=word.spoilers[5]
    }
    private fun slideWords(){
        listOf(binding.textContainer,binding.linearLayoutOyun).forEach {
            val firstAnimator= ObjectAnimator.ofFloat(it,"translationX", 0f, -1000f).apply {
                duration = 200
            }
            val secondAnimator= ObjectAnimator.ofFloat(it,"translationX", 1000f, 0f).apply {
                duration = 200
            }
            firstAnimator.addListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        secondAnimator.start()
                    }
                }
            )
            firstAnimator.start()
        }
    }
    private fun checkMainWord():Boolean{
        return word.mainWord.lowercase()==customMainWord.getCombinedWord().lowercase()
    }

    private fun passLimitReached(binding: ActivitySoloGameBinding){
        val shrinkAnimationLeft = ScaleAnimation(
            1f, 0f,
            1f, 0f,
            Animation.RELATIVE_TO_SELF, 0.9f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        val shrinkAnimationRight = ScaleAnimation(
            1f, 0f,
            1f, 0f,
            Animation.RELATIVE_TO_SELF, 0.1f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        shrinkAnimationLeft.duration = 500
        shrinkAnimationRight.duration = 500

        binding.nextSolo.startAnimation(shrinkAnimationLeft)
        binding.nextCountSolo.startAnimation(shrinkAnimationRight)
        shrinkAnimationLeft.setAnimationListener(
            object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    binding.nextSolo.visibility=View.GONE
                    binding.nextCountSolo.visibility=View.GONE
                }
                override fun onAnimationRepeat(animation: Animation?) {}
            }
        )
    }
}