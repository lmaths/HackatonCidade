package com.rightside.hackaton.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.view.contracts.FeedContract

class HelloWorldPresenter(private val service : FeedContract.FirebaseService) : FeedContract.Presenter, LifecycleObserver {
    override var view: FeedContract.View? = null
    lateinit var lifecycle : Lifecycle
    override fun getHelloWorld() {
        view?.helloWorld(service.sendHelloWorld())
    }

    override fun init() {
        lifecycle.addObserver(this)
        getHelloWorld()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        view = null
    }

}