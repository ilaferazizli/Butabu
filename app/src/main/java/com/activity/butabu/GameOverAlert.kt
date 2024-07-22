package com.activity.butabu

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.nextWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import com.activity.butabu.activities.GameOverActivity
import com.activity.butabu.databinding.AlertGameOverBinding
import com.activity.butabu.objects.GameProperties.roundTotal
import com.activity.butabu.objects.GameProperties.roundCurrent

class GameOverAlert(private val context: Context, private val customCountDownTimer: CustomCountDownTimer) {
    
    private var alertDialog: AlertDialog? = null

    fun showGameOverDialog() {
        val builder = AlertDialog.Builder(context)
        val view = AlertGameOverBinding.inflate(LayoutInflater.from(context))
        builder.setView(view.root)

        view.start.setOnClickListener {
            alertDialog?.dismiss()
            cancelledWord = 0
            nextWord = 0
            correctWord = 0
            if(roundCurrent> roundTotal){
                val intent = Intent(context,GameOverActivity::class.java)
                context.startActivity(intent)
            }
            else
                customCountDownTimer.restart()
        }
        "Round $roundCurrent/$roundTotal".also { view.round.text = it }
        if(roundCurrent== roundTotal&& !Team2.played){
            "Nəticələr".also { view.start.text = it }
            view.nextTeam.visibility = INVISIBLE
        }
        if (!Team1.played){
            Team1.name.also{ view.teamCurrent.text = it}
            "Növbəti ${ Team2.name }".also{ view.nextTeam.text = it}
            Team1.totalNext+= nextWord
            Team1.totalCancelled+= cancelledWord
            Team2.played=false
            Team1.played=true
        }
        else{
            Team2.name.also{ view.teamCurrent.text = it}
            "Növbəti ${ Team1.name }".also{ view.nextTeam.text = it}
            Team2.totalNext+= nextWord
            Team2.totalCancelled+= cancelledWord
            Team1.played=false
            Team2.played=true
            roundCurrent++
        }


        view.cancelCount.text = cancelledWord.toString()
        view.nextCount.text = nextWord.toString()
        view.doneCount.text = correctWord.toString()
        alertDialog = builder.create()
        alertDialog?.setCancelable(false)
        alertDialog?.window?.setBackgroundDrawableResource(R.drawable.bg_result_stroke)
        alertDialog?.show()
    }
}