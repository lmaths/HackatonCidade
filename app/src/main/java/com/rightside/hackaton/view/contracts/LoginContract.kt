package com.rightside.hackaton.view.contracts

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface LoginContract {
    interface View : BaseView<Presenter> {
        fun finishFragment()
    }
    interface Presenter : BasePresenter<View> {
        fun saveUserId(user: User)
    }

    interface FirebaseService {
        fun saveUserIds(user: User)
    }
}