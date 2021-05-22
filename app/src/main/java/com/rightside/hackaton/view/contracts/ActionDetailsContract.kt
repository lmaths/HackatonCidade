package com.rightside.hackaton.view.contracts


import com.rightside.hackaton.view.base.BasePresenter
import com.rightside.hackaton.view.base.BaseView


interface ActionDetailsContract {
    interface View : BaseView<Presenter> {

    }
    interface Presenter : BasePresenter<View> {

    }

    interface FirebaseService {

    }
}