package com.activity.butabu.objects

object GameProperties {
    var roundTotal = 4
    var roundCurrent = 1
    var passCount = 3
    var time=60
    var warningSound = false
    var language="Azerbaycan dili"
    fun reset(){
        roundCurrent = 1
    }
}