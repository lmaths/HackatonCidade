package com.rightside.hackaton.iteractor

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.FeedContract
import io.reactivex.Observable

class FeedIteractor(private val dbInstance : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : FeedContract.FirebaseService {
    override fun getFeed(): Observable<List<Action>> {
        return Observable.create { emmiter ->
            dbInstance.collection("actions").addSnapshotListener { value, error ->
                value?.toObjects(Action::class.java)?.let { emmiter.onNext(it) }
            }
        }
    }

    override fun verifyIfUserIsAuthenticated() : Boolean {
        appSharedPreferences.userId?.let { return true }
        return false
    }


}