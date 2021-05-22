package com.rightside.hackaton.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.ActionDetailsContract
import io.reactivex.disposables.CompositeDisposable

class ActionDetailsPresenter(private val service : ActionDetailsContract.FirebaseService, private val schedulerProvider: BaseSchedulerProvider) : ActionDetailsContract.Presenter, LifecycleObserver {

    override var view: ActionDetailsContract.View? = null
    lateinit var lifecycle: Lifecycle
    private val disposable = CompositeDisposable()

    override fun buyAction(action: Action) {
        service.buyAction(action)
    }

    override fun init() {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycle.removeObserver(this)
        disposable.clear()
        view = null
    }


}