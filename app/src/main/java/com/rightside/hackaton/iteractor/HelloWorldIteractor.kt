package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.view.contracts.FeedContract

class HelloWorldIteractor() : FeedContract.FirebaseService {

    override fun sendHelloWorld() : String {
        return "Hello World"
    }
}