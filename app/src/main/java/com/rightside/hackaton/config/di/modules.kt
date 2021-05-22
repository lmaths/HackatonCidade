package com.rightside.hackaton.config.di

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.iteractor.HelloWorldIteractor
import com.rightside.hackaton.iteractor.LoginIteractor
import com.rightside.hackaton.presenter.HelloWorldPresenter
import com.rightside.hackaton.presenter.LoginPresenter
import com.rightside.hackaton.view.contracts.FeedContract
import com.rightside.hackaton.view.contracts.LoginContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val presenterModules = module {
    single<BaseSchedulerProvider>{SchedulerProvider()}
    factory { HelloWorldPresenter(service = get()) }
    factory { LoginPresenter(service = get(), schedulerProvider = get()) }
}

val iteractorModules = module {
    factory { FirebaseFirestore.getInstance() }
    single<FeedContract.FirebaseService>{ HelloWorldIteractor(get(), appSharedPreferences = get()) }
    single<LoginContract.FirebaseService> { LoginIteractor(get(), appSharedPreferences = get()) }
}
val dbModules = module {
    single { AppSharedPreferences(context = get()) }
}


val modules : List<Module> = listOf(dbModules, iteractorModules, presenterModules)