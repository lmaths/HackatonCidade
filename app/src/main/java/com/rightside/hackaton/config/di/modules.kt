package com.rightside.hackaton.config.di

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.iteractor.HelloWorldIteractor
import com.rightside.hackaton.presenter.HelloWorldPresenter
import com.rightside.hackaton.view.contracts.FeedContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val presenterModules = module {
    single<BaseSchedulerProvider>{SchedulerProvider()}
    factory { HelloWorldPresenter(service = get()) }
}

val iteractorModules = module {
    factory { FirebaseFirestore.getInstance() }
    single<FeedContract.FirebaseService>{ HelloWorldIteractor() }
}


val modules : List<Module> = listOf(iteractorModules, presenterModules)