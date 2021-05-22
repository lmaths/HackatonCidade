package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.view.contracts.FeedContract

class HelloWorldIteractor(private val dbInstance : FirebaseFirestore) : FeedContract.FirebaseService {

    override fun sendHelloWorld() : String {
        dbInstance.collection("feed").document("10").set(mapOf("Hello" to "teste"))
        return "Hello World"
    }
}