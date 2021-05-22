package com.rightside.hackaton.view.contracts


import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView

interface FeedContract {
    interface View : BaseView<Presenter> {
        fun helloWorld(value : String)
        fun showLogin()
    }
    interface Presenter : BasePresenter<FeedContract.View> {
        fun getHelloWorld()
    }

    interface FirebaseService {
        fun sendHelloWorld() : Boolean
    }
}

