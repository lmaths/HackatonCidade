package com.rightside.hackaton.presenter

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.contracts.ProducerDetailsContract
import io.reactivex.disposables.CompositeDisposable

class ProducerDetailsPresenter(private val service : ProducerDetailsContract.FirebaseService, private val schedulerProvider: BaseSchedulerProvider) : ProducerDetailsContract.Presenter, LifecycleObserver {
    override var view: ProducerDetailsContract.View? = null
    lateinit var lifecycle : Lifecycle
    private val disposable = CompositeDisposable()

    override fun getAllActivesByIdProducer(producerId: String) {
        disposable.add(
        service.getAllActivesByIdProducer(producerId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { view?.showLoading() }
            .subscribe( { onSuccessGetActionsByIdProducer(it) }, {
                onFailure(it)
            })
        )
    }

    private fun onSuccessGetActionsByIdProducer(it: List<Action>) {
        if (!it.isNullOrEmpty()) {
            view?.updateActions(it)
        }
    }

    private fun onFailure(it: Throwable?) {

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