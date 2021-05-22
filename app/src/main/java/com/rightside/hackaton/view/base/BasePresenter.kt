package com.rightside.hackaton.view.base

interface BasePresenter<T> {
    fun init()
    var view : T?
}