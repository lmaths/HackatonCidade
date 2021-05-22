package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.contracts.ProfileContract
import io.reactivex.Observable

class ProfileIteractor(private val dbInstance : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : ProfileContract.FirebaseService {

    override fun verifyIfUserIsAuthenticated(): String? {
        return appSharedPreferences.userId
    }

    override fun getUserData(userId: String): Observable<User> {
       return Observable.create {  emmiter ->
            dbInstance.collection("users").document(userId).addSnapshotListener { value, error ->
                value?.toObject(User::class.java)?.let { emmiter.onNext(it) }
            }
        }
    }

    override fun getActionsPurchasedByUserId(userid: String): Observable<List<Action>> {
        return Observable.create { emmiter ->
                dbInstance.collection("")
        }
    }

}