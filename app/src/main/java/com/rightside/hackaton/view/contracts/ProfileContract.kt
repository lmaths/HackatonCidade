package com.rightside.hackaton.view.contracts

import com.rightside.hackaton.model.Action
import com.rightside.hackaton.model.User
import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView
import io.reactivex.Observable

interface ProfileContract {
    interface View : BaseView<Presenter> {
        fun moveUserToLogin()
        fun updateData(userdata: User, actions: List<Action>)
    }
    interface Presenter : BasePresenter<View> {

    }

    interface FirebaseService {
        fun verifyIfUserIsAuthenticated() : String?
        fun getUserData(userId: String) : Observable<User>
        fun getActionsPurchasedByUserId(userid : String) : Observable<List<Action>>
    }
}