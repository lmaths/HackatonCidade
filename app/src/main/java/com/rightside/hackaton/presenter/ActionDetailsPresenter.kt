package com.rightside.hackaton.presenter

import com.rightside.hackaton.config.di.BaseSchedulerProvider
import com.rightside.hackaton.view.contracts.ActionDetailsContract

class ActionDetailsPresenter(private val service : ActionDetailsIteractor, private val schedulerProvider: BaseSchedulerProvider) : ActionDetailsContract.Presenter {

    override var view: ActionDetailsContract.View? = null

    override fun init() {

    }



}