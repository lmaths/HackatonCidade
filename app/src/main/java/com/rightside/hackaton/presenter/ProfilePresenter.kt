package com.rightside.hackaton.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.contracts.ProfileContract
import io.reactivex.disposables.CompositeDisposable

class ProfilePresenter(val service : ProfileContract.FirebaseService, val baseSchedulerProvider: BaseSchedulerProvider) : ProfileContract.Presenter, LifecycleObserver {
    override var view: ProfileContract.View? = null
    lateinit var lifecycle : Lifecycle
    private val disposable = CompositeDisposable()

    override fun init() {
        lifecycle.addObserver(this)
        getUserData()
    }

    private fun getUserData() {
        val userId = service.verifyIfUserIsAuthenticated()
        userId?.let {
            disposable.add(
            service.getUserData(it)
                .zipWith(service.getActionsPurchasedByUserId(it),{ userdata, actions -> onFinished(userdata, actions)})
                .subscribeOn(baseSchedulerProvider.io())
                .observeOn(baseSchedulerProvider.ui())
                .subscribe()
            )
        } ?: view?.moveUserToLogin()
    }

    private fun onFinished(userdata: User, actions: List<Action>) {
       view?.updateData(userdata, actions)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycle.removeObserver(this)
        disposable.clear()
        view = null
    }


}