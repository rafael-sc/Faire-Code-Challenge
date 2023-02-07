package com.orafaelsc.fairetest.di

import com.orafaelsc.fairetest.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

// object to hold MainScreen dependencies
object MainModule {
    val module: Module = module {
        viewModel {
            MainViewModel(
                dispatcherProvider = get()
            )
        }
    }
}
