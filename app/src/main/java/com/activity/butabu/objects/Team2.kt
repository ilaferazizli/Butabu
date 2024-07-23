package com.activity.butabu.objects

object Team2 {
    var name="Komanda 2"
    var totalCancelled = 0
    var totalCorrect = 0
    var totalNext = 0
    var played=false
    fun reset(){
        Team1.totalCancelled = 0
        Team1.totalCorrect = 0
        Team1.totalNext = 0
        Team1.played =false
    }
}