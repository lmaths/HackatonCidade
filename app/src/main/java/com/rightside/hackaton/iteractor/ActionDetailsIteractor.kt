package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.view.contracts.ActionDetailsContract

class ActionDetailsIteractor(private val dbService : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : ActionDetailsContract.FirebaseService {

}