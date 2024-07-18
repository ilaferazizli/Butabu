package com.activity.butabu

import android.app.AlertDialog
import android.content.Context
import com.activity.butabu.objects.WordCounts.cancelledWord
import com.activity.butabu.objects.WordCounts.nextWord
import com.activity.butabu.objects.WordCounts.correctWord
import com.activity.butabu.objects.Team1
import com.activity.butabu.objects.Team2
import android.view.LayoutInflater
import com.activity.butabu.databinding.AlertGameOverBinding

class CustomAlertDialog(private val context: Context, private val customCountDownTimer: CustomCountDownTimer) {

    private var alertDialog: AlertDialog? = null

    fun showGameOverDialog() {
        val builder = AlertDialog.Builder(context)
        val view = AlertGameOverBinding.inflate(LayoutInflater.from(context))
        builder.setView(view.root)

        view.geri.setOnClickListener {
            alertDialog?.dismiss()
        }

        view.basla.setOnClickListener {
            alertDialog?.dismiss()
            customCountDownTimer.restart()
        }

        view.cancelCount.text = cancelledWord.toString()
        view.nextCount.text = nextWord.toString()
        view.doneCount.text = correctWord.toString()

        Team1.totalCancelled += cancelledWord
        Team1.totalCorrect += correctWord
        Team1.totalNext += nextWord

        Team2.totalCancelled += cancelledWord
        Team2.totalCorrect += correctWord
        Team2.totalNext += nextWord

        alertDialog = builder.create()
        alertDialog?.window?.setBackgroundDrawableResource(R.drawable.bg_result_stroke)
        alertDialog?.show()
    }
}