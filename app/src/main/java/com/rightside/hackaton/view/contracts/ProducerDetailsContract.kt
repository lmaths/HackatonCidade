package com.rightside.hackaton.view.contracts

import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView
import io.reactivex.Observable

interface ProducerDetailsContract {
    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
        fun updateActions(it: List<Action>)

    }
    interface Presenter : BasePresenter<View> {
        fun getAllActivesByIdProducer(producerId : String)
    }

    interface FirebaseService {
        fun getAllActivesByIdProducer(producerId: String) : Observable<List<Action>>
    }
}