package com.rightside.hackaton.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.FeedContract
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class FeedPresenter(private val service : FeedContract.FirebaseService, private val schedulerProvider: BaseSchedulerProvider) : FeedContract.Presenter, LifecycleObserver {
    override var view: FeedContract.View? = null
    lateinit var lifecycle : Lifecycle
    private val disposable = CompositeDisposable()

    override fun getFeed() {
        disposable.add(
        service.getFeed()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { view?.showLoading() }
                .delay(2, TimeUnit.SECONDS, schedulerProvider.ui())
                .subscribe({ onFinishedGetFeed(it) }, { onFailureGetFeed(it) })
        )
    }

    private fun onFailureGetFeed(it: Throwable) {
        view?.hideLoading()
    }

    private fun onFinishedGetFeed(it: List<Action>) {
        view?.hideLoading()
        if (!it.isNullOrEmpty()) {
            view?.updateFeed(it)
        } else {
            view?.showEmptyFeed()
        }
    }


    override fun init() {
        lifecycle.addObserver(this)
        getFeed()
    }

    override fun moveToDetails(action: Action) {
        when(service.verifyIfUserIsAuthenticated()) {
            true -> view?.showDetails(action)
            false -> view?.moveToLogin()
        }
       service.verifyIfUserIsAuthenticated()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        view = null
    }
}