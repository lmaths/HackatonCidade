package com.rightside.hackaton.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.view.contracts.ProducerContract
import io.reactivex.disposables.CompositeDisposable

class ProducerPresenter(private val service : ProducerContract.FirebaseService, private val schedulerProvider: BaseSchedulerProvider) : ProducerContract.Presenter, LifecycleObserver {
    override var view: ProducerContract.View? = null
    lateinit var lifecycle : Lifecycle
    private val disposable = CompositeDisposable()

    override fun getAllProducers() {
        disposable.add(
            service.getAllProducers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { view?.showLoading() }
                .subscribe( { onFinishedGetAllProducers(it) } , { onFailureGetAllProducers(it) })
        )
    }

    private fun onFinishedGetAllProducers(it: List<Producer>) {
         view?.hideLoading()
        if (!it.isNullOrEmpty()) {
            view?.showProducers(it)
        } else {
            view?.showEmptyProducers()
        }
    }

    private fun onFailureGetAllProducers(it: Throwable) {
        view?.hideLoading()
        //todo server error
    }

    override fun init() {
        lifecycle.addObserver(this)
        getAllProducers()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycle.removeObserver(this)
        disposable.clear()
        view = null
    }

    override fun moveToProducerDetails(producer: Producer) {
       when(service.verifyIfUserIsAuthenticated()) {
           true -> view?.moveToProducerDetails(producer)
           false -> view?.moveToLogin()
       }
    }

}