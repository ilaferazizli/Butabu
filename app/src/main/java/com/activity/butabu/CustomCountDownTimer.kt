package com.activity.butabu

import android.os.CountDownTimer

open class CustomCountDownTimer (
    private var millisInFuture: Long,
    private var countDownInterval: Long

){
    private var millisUntilFinished: Long = millisInFuture
    private var internalTimer=InternalTimer(this, millisInFuture, countDownInterval)
    private var isRunning: Boolean = false
    var onTickListener: ((millisUntilFinished: Long) -> Unit)? = null
    var onFinishListener: (() -> Unit)? = null
    var onResumeListener: (() -> Unit)? = null


    private class InternalTimer(
        private val parent: CustomCountDownTimer,
        millisInFuture: Long,
        countDownInterval: Long
    ): CountDownTimer(millisInFuture, countDownInterval){
        var millisUntilFinished: Long = parent.millisUntilFinished
        override fun onTick(millisUntilFinished: Long) {
            this.millisUntilFinished = millisUntilFinished
            parent.onTickListener?.invoke(millisUntilFinished)

        }

        override fun onFinish() {
            parent.millisUntilFinished = 0
            parent.isRunning = false
            parent.onFinishListener?.invoke()
        }

    }
    fun pauseTimer(){
        internalTimer.cancel()
        isRunning = false

    }
    fun resumeTimer(){
        if(!isRunning && millisUntilFinished > 0){
            internalTimer = InternalTimer(this,internalTimer.millisUntilFinished,countDownInterval)
            start()
        }
    }
    fun start(){
        isRunning=true
        internalTimer.start()
    }
    fun restart(){
        internalTimer.cancel()
        internalTimer = InternalTimer(this,millisInFuture,countDownInterval)
        start()
    }
    fun destroy(){
        internalTimer.cancel()
    }

}