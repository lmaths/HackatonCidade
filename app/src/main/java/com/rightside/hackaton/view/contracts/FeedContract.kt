package com.rightside.hackaton.view.contracts


import com.rightside.hackaton.model.Action
import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView
import io.reactivex.Observable

interface FeedContract {
    interface View : BaseView<Presenter> {
        fun showLogin()
        fun showLoading()
        fun updateFeed(it: List<Action>)
        fun showEmptyFeed()
        fun hideLoading()
        fun showDetails(action: Action)
        fun moveToLogin()
    }
    interface Presenter : BasePresenter<FeedContract.View> {
        fun getFeed()
        fun moveToDetails(action: Action)
    }

    interface FirebaseService {
        fun getFeed() : Observable<List<Action>>
        fun verifyIfUserIsAuthenticated() : Boolean
    }
}

