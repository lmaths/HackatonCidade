package com.rightside.hackaton.view.contracts


import com.rightside.hackaton.model.Producer
import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView
import io.reactivex.Observable


interface ProducerContract {
    interface View : BaseView<Presenter> {
        fun showLoading()
        fun showProducers(it: List<Producer>)
        fun showEmptyProducers()
        fun hideLoading()
        fun moveToProducerDetails(producer: Producer)
        fun moveToLogin()
    }
    interface Presenter : BasePresenter<View> {
       fun getAllProducers()
        fun moveToProducerDetails(producer: Producer)
    }

    interface FirebaseService {
        fun getAllProducers() : Observable<List<Producer>>
        fun verifyIfUserIsAuthenticated() : Boolean
    }
}