package com.activity.butabu

import android.content.Context
import com.activity.butabu.activities.Words

class Database {
    var wordList=ArrayList<Words>()

    fun createSQLdb(context: Context){
        val dbWords=context.openOrCreateDatabase("words", Context.MODE_PRIVATE, null)
        dbWords.execSQL("CREATE TABLE IF NOT EXISTS words (id INTEGER PRIMARY KEY AUTOINCREMENT, MainWord VARCHAR, category VARCHAR, difficulty VARCHAR, language VARCHAR, prohibitedWords VARCHAR)")
        dbWords.execSQL("INSERT INTO words (MainWord, category, difficulty, language, prohibitedWords) VALUES ('word1', 'category1', 'sade', 'language1', 'prohibitedWords1')")
        dbWords.execSQL("INSERT INTO words (MainWord, category, difficulty, language, prohibitedWords) VALUES ('word2', 'category1', 'sade', 'language1', 'prohibitedWords2')")
        dbWords.execSQL("INSERT INTO words (MainWord, category, difficulty, language, prohibitedWords) VALUES ('word3', 'category1', 'sade', 'language1', 'prohibitedWords3')")
    }
    fun readSQLdb(context: Context){
        val dbWords=context.openOrCreateDatabase("words", Context.MODE_PRIVATE, null)
        val cursor=dbWords.rawQuery("SELECT * FROM words", null)
        val wordIndex=cursor.getColumnIndex("MainWord")
        val categoryIndex=cursor.getColumnIndex("category")
        val difficultyIndex=cursor.getColumnIndex("difficulty")
        val languageIndex=cursor.getColumnIndex("language")
        val prohibitedWordsIndex=cursor.getColumnIndex("prohibitedWords")
        while (cursor.moveToNext()){
            val word=Words(cursor.getString(wordIndex), cursor.getString(categoryIndex), cursor.getString(difficultyIndex), cursor.getString(languageIndex), cursor.getString(prohibitedWordsIndex))
            wordList.add(word)
        }
        cursor.close()
    }
}