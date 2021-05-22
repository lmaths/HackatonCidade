package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.view.contracts.ProducerContract
import io.reactivex.Observable

class ProducerIteractor(private val dbInstance : FirebaseFirestore, private val appSharedPreferences: AppSharedPreferences) : ProducerContract.FirebaseService {

    override fun getAllProducers(): Observable<List<Producer>> {
       return Observable.create { emmiter ->
           dbInstance.collection("producers").addSnapshotListener { value, error ->
               value?.toObjects(Producer::class.java)?.let { emmiter.onNext(it) }
           }
       }
    }

    override fun verifyIfUserIsAuthenticated() : Boolean {
        appSharedPreferences.userId?.let { return true }
        return false
    }

}