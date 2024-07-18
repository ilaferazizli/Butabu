package com.activity.butabu.dataclasses

data class Words (
    val word: String,
    val category: String,
    val difficulty: String,
    val language: String,
    val prohibitedWords: List<String>
)