package com.activity.butabu.objects

import android.util.Log
import com.activity.butabu.dataclasses.Words
import com.activity.butabu.dataclasses.WordsSolo
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class FireStoreRepository{
    companion object {
        var wordList = ArrayList<Words>()
        var wordListSolo = ArrayList<WordsSolo>()
        val usedWordList = ArrayList<Words>()
    }
    var staticWordList = ArrayList<Words>()

//    fun fetchData(onFetchComplete: (ArrayList<Words>) -> Unit) {
//        val db = Firebase.firestore
//        db.collection("MainWords")
//            .get()
//            .addOnSuccessListener { result ->
//                Log.d(
//                    "FirestoreRepository",
//                    "Words fetched successfully"
//                )
//                for (document in result) {
//                    val word = document.toObject(Words::class.java)
//                    when (word.difficulty) {
//                        "sade" -> {
//                            easyWordList.add(word)
//                            Log.d("FirestoreRepository", "Words fetched successfully")
//                        }
//                        "orta" -> mediumWordList.add(word)
//                        "cetin" -> hardWordList.add(word)
//                    }
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e(
//                    "FirestoreRepository",
//                    "Error fetching words: ${exception.message}"
//                )
//            }
//            .addOnCompleteListener {
//                onFetchComplete(easyWordList)
//            }
//    }
    fun fetchDataForSolo(onFetchComplete: ()-> Unit) {
        val db = Firebase.firestore
        db.collection("SoloMainWords")
            .get()
            .addOnSuccessListener { result ->
                Log.d(
                    "FirestoreRepository",
                    "Words fetched successfully"
                )
                for (document in result) {
                    val word = document.toObject(WordsSolo::class.java)
                    wordListSolo.add(word)
                    Log.d("FirestoreRepository", "Words fetched successfully")
                }
            }
            .addOnFailureListener { exception ->
                Log.e(
                    "FirestoreRepository",
                    "Error fetching words: ${exception.message}"
                )
            }
            .addOnCompleteListener {
                onFetchComplete()
            }
    }
    fun writeData(word: Words) {
        val db = FirebaseFirestore.getInstance()
        val wordsCollection = db.collection("MainWords")

        wordsCollection.whereEqualTo("mainWord", word.mainWord)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    wordsCollection.add(word)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Firestore", "Word added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Error adding word", e)
                        }
                } else {
                    Log.d("Firestore", "Word already exists")
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error checking word", e)
            }
    }
    fun fetchAndDeleteData() {
        val db = Firebase.firestore
        db.collection("MainWords")
            .get()
            .addOnSuccessListener { result ->
                Log.d("FirestoreRepository", "Words fetched successfully")
                for (document in result) {
                    val word = document.toObject(Words::class.java)
                    if (word.mainWord.length > 9) {
                        db.collection("MainWords").document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                Log.d("FirestoreRepository", "Document with word ${word.mainWord} successfully deleted")
                            }
                            .addOnFailureListener { exception ->
                                Log.e("FirestoreRepository", "Error deleting document: ${exception.message}")
                            }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirestoreRepository", "Error fetching words: ${exception.message}")
            }
    }
    fun writeDataForSolo(word: WordsSolo) {
        val db = FirebaseFirestore.getInstance()
        val wordsCollection = db.collection("SoloMainWords")

        wordsCollection.whereEqualTo("mainWord", word.mainWord)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    wordsCollection.add(word)
                        .addOnSuccessListener { documentReference ->
                            Log.d("Firestore", "Word added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.e("Firestore", "Error adding word", e)
                        }
                } else {
                    Log.d("Firestore", "Word already exists")
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error checking word", e)
            }
    }
    fun fetchData(searchWith: String, searchValue: String, onFetchComplete: () -> Unit) {
        val db = Firebase.firestore
        wordList.clear()

        db.collection("MainWords")
            .whereEqualTo(searchWith, searchValue)
            .get()
            .addOnSuccessListener { result ->
                Log.d(
                    "FirestoreRepository",
                    "Words fetched successfully"
                )
                for (document in result) {
                    val word = document.toObject(Words::class.java)
                    wordList.add(word)
                }
            }
            .addOnFailureListener { exception ->
                Log.e(
                    "FirestoreRepository",
                    "Error fetching words: ${exception.message}"
                )
            }
            .addOnCompleteListener {
                onFetchComplete()
            }
    }



}