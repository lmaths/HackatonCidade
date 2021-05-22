package com.rightside.hackaton.presenter

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.contracts.FeedContract
import com.rightside.hackaton.view.contracts.LoginContract
import io.reactivex.disposables.CompositeDisposable

class LoginPresenter(private val service : LoginContract.FirebaseService, private val schedulerProvider: BaseSchedulerProvider) : LoginContract.Presenter {
    override var view: LoginContract.View? = null
    override fun saveUserId(user: User) {
        service.saveUserIds(user)
    }


    override fun init() {
    }



}