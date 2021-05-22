package com.rightside.hackaton.iteractor

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.ProducerDetailsContract
import io.reactivex.Observable

class ProducerDetailsIteractor(val dbInstance : FirebaseFirestore, val appSharedPreferences: AppSharedPreferences) : ProducerDetailsContract.FirebaseService {
    override fun getAllActivesByIdProducer(producerId : String): Observable<List<Action>> {
        return Observable.create { emmiter ->
            dbInstance.collection("actions").whereEqualTo("producerId", producerId).addSnapshotListener { value, error ->
                value?.toObjects(Action::class.java)?.let { emmiter.onNext(it) }
            }
        }
    }
}