package com.rightside.hackaton.iteractor

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.contracts.LoginContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class LoginIteractor(private val dbInstance : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : LoginContract.FirebaseService {
    override fun saveUserIds(user: User) {
        appSharedPreferences.userName = user.name
        appSharedPreferences.userId = user.uuid
    }

}