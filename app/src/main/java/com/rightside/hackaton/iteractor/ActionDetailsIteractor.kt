package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.ActionDetailsContract

class ActionDetailsIteractor(private val dbService : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : ActionDetailsContract.FirebaseService {
    override fun buyAction(action: Action) {
        dbService.collection(appSharedPreferences.userId.toString()).document(action.id).set(action)
    }

}