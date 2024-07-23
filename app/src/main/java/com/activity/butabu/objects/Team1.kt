package com.activity.butabu.objects

object Team1 {
    var name="Komanda 1"
    var totalCancelled = 0
    var totalCorrect = 0
    var totalNext = 0
    var played=false
    fun reset(){
        totalCancelled = 0
        totalCorrect = 0
        totalNext = 0
        played=false
    }
}