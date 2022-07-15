package com.estarta.listingapp.injection

import com.estarta.listingapp.vvm.listings.ListingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ViewModelsModule : () -> Module {
    override fun invoke(): Module = module {
        viewModel { ListingsViewModel(get(), get()) }
    }
}