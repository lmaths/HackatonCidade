package com.rightside.hackaton.view.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}