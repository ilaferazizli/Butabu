package com.activity.butabu

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.activity.butabu.databinding.ActivityGameBinding
import com.activity.butabu.databinding.AlertQuitWarningBinding
import com.activity.butabu.objects.GameProperties.roundCurrent
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.objects.WordCounts.nextWord

class QuitWarning(private val context: Context,private val customCountDownTimer: CustomCountDownTimer) {

    private var alertDialog: AlertDialog? = null

    fun showGameOverDialog() {
        val builder = AlertDialog.Builder(context)
        val view = AlertQuitWarningBinding.inflate(LayoutInflater.from(context))
        val gameActivityBinding = ActivityGameBinding.inflate(LayoutInflater.from(context))
        builder.setView(view.root)

        customCountDownTimer.pauseTimer()
        view.no.setOnClickListener{
            alertDialog?.dismiss()
            customCountDownTimer.resumeTimer()
        }
        view.yes.setOnClickListener {
            (context as? Activity)?.finish()
            customCountDownTimer.destroy()
            resetValues()
            changeTimerColor(R.color.green, gameActivityBinding)
        }
        alertDialog = builder.create()
        alertDialog?.window?.setBackgroundDrawableResource(R.drawable.bg_quit_warning)

        alertDialog?.show()
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
        roundCurrent=1
    }
    private fun changeTimerColor(color:Int, binding: ActivityGameBinding){
        val resources = context.resources
        binding.countTime.progressDrawable.setTint(resources.getColor(color))
        binding.timeCircle.setTextColor(resources.getColor(color))
    }
}