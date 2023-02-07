package com.orafaelsc.fairetest.di

import com.orafaelsc.fairetest.commom.CoroutineDispatcherProvider
import com.orafaelsc.fairetest.commom.ResourceProvider
import com.orafaelsc.fairetest.commom.ResourceProviderImpl
import org.koin.dsl.module

// object to hold MainScreen dependencies
object ProvidersModule {
    val instance = module {
        single<ResourceProvider> { ResourceProviderImpl(get()) }

        single<CoroutineDispatcherProvider> {
            CoroutineDispatcherProvider()
        }
    }
}
