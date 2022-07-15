package com.estarta.listingapp.injection

import com.estarta.listingapp.data.repositories.listings.ListingsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoriesModule : () -> Module {
    override fun invoke(): Module = module {
        factory { ListingsRepository(get()) }
    }
}