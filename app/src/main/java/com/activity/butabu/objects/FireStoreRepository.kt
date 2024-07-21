package com.activity.butabu.objects

import android.util.Log
import com.activity.butabu.dataclasses.Words
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FireStoreRepository{
    companion object {
        var easyWordList = ArrayList<Words>()
        var mediumWordList = ArrayList<Words>()
        var hardWordList = ArrayList<Words>()
        val usedWordList = ArrayList<Words>()
    }
    var staticWordList = ArrayList<Words>()

    fun fetchData(onFetchComplete: (ArrayList<Words>) -> Unit) {
        val db = Firebase.firestore
        db.collection("MainWords")
            .get()
            .addOnSuccessListener { result ->
                Log.d(
                    "FirestoreRepository",
                    "Words fetched successfully"
                )
                for (document in result) {
                    val word = document.toObject(Words::class.java)
                    when (word.difficulty) {
                        "sade" -> {
                            easyWordList.add(word)
                            Log.d("FirestoreRepository", "Words fetched successfully")
                        }
                        "orta" -> mediumWordList.add(word)
                        "cetin" -> hardWordList.add(word)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e(
                    "FirestoreRepository",
                    "Error fetching words: ${exception.message}"
                )
            }
            .addOnCompleteListener {
                onFetchComplete(easyWordList)
            }
    }
    fun writeData(word: Words) {
        val db = FirebaseFirestore.getInstance()
        val wordsCollection = db.collection("MainWords")

        wordsCollection.add(word)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Word added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding word", e)
            }
    }
}