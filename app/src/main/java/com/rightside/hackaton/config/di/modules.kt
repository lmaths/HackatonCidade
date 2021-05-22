package com.rightside.hackaton.config.di

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.iteractor.FeedIteractor
import com.rightside.hackaton.iteractor.LoginIteractor
import com.rightside.hackaton.iteractor.ProducerIteractor
import com.rightside.hackaton.presenter.FeedPresenter
import com.rightside.hackaton.presenter.LoginPresenter
import com.rightside.hackaton.presenter.ProducerPresenter
import com.rightside.hackaton.view.contracts.FeedContract
import com.rightside.hackaton.view.contracts.LoginContract
import com.rightside.hackaton.view.contracts.ProducerContract
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val presenterModules = module {
    single<BaseSchedulerProvider>{SchedulerProvider()}
    factory { FeedPresenter(service = get(), schedulerProvider = get()) }
    factory { LoginPresenter(service = get(), schedulerProvider = get()) }
    factory { ProducerPresenter(service = get(), schedulerProvider = get() ) }
}

val iteractorModules = module {
    factory { FirebaseFirestore.getInstance() }
    single<FeedContract.FirebaseService>{ FeedIteractor(get(), appSharedPreferences = get()) }
    single<LoginContract.FirebaseService> { LoginIteractor(get(), appSharedPreferences = get()) }
    single<ProducerContract.FirebaseService> {ProducerIteractor(get(), appSharedPreferences = get() )}
}
val dbModules = module {
    single { AppSharedPreferences(context = get()) }
}


val modules : List<Module> = listOf(dbModules, iteractorModules, presenterModules)