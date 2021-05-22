package com.rightside.hackaton.config.di

import com.google.firebase.firestore.FirebaseFirestore
import com.rightside.hackaton.config.preferences.AppSharedPreferences
import com.rightside.hackaton.iteractor.*
import com.rightside.hackaton.presenter.*
import com.rightside.hackaton.view.contracts.*
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val presenterModules = module {
    single<BaseSchedulerProvider>{SchedulerProvider()}
    factory { FeedPresenter(service = get(), schedulerProvider = get()) }
    factory { LoginPresenter(service = get(), schedulerProvider = get()) }
    factory { ProducerPresenter(service = get(), schedulerProvider = get() ) }
    factory { ProducerDetailsPresenter(service = get(), schedulerProvider = get()) }
    factory { ProfilePresenter(service = get(), baseSchedulerProvider = get() ) }
    factory { ActionDetailsIteractor(dbService = get(),appSharedPreferences = get()) }
}

val iteractorModules = module {
    factory { FirebaseFirestore.getInstance() }
    single<FeedContract.FirebaseService>{ FeedIteractor(get(), appSharedPreferences = get()) }
    single<LoginContract.FirebaseService> { LoginIteractor(get(), appSharedPreferences = get()) }
    single<ProducerContract.FirebaseService> {ProducerIteractor(get(), appSharedPreferences = get() ) }
    single<ProducerDetailsContract.FirebaseService> { ProducerDetailsIteractor(dbInstance = get(), appSharedPreferences = get() ) }
    single<ProfileContract.FirebaseService> { ProfileIteractor(dbInstance = get(), appSharedPreferences = get() )}
    single<ActionDetailsContract.FirebaseService> {ActionDetailsIteractor(dbService = get(), appSharedPreferences = get() )}
}
val dbModules = module {
    single { AppSharedPreferences(context = get()) }
}


val modules : List<Module> = listOf(dbModules, iteractorModules, presenterModules)