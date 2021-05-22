package com.rightside.hackaton.iteractor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.view.contracts.FeedContract

class HelloWorldIteractor(private val dbInstance : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : FeedContract.FirebaseService {

    override fun sendHelloWorld() : Boolean {
        val userId = appSharedPreferences.userId
        userId?.let {
            dbInstance.collection("feed").document("10").set(mapOf("Hello" to "teste"))
            return true
        }
        return false
    }
}