package com.orafaelsc.fairetest

import android.app.Application
import com.orafaelsc.fairetest.di.ForecastModule
import com.orafaelsc.fairetest.di.MainModule
import com.orafaelsc.fairetest.di.ProvidersModule
import com.orafaelsc.fairetest.di.RetrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(RetrofitModule.instance, MainModule.module, ForecastModule.instance, ProvidersModule.instance)
            androidContext(this@MyApplication)
        }
    }
}
