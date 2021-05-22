package com.rightside.hackaton.config.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface BaseSchedulerProvider {
    fun io() : Scheduler
    fun computation() : Scheduler
    fun ui() : Scheduler
}
class SchedulerProvider : BaseSchedulerProvider{
    override fun io() = Schedulers.io()
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
}