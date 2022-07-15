package com.estarta.listingapp.injection
import com.estarta.listingapp.data.datasource.ListingDataSourceFactory
import com.estarta.listingapp.data.services.ApiService
import com.estarta.listingapp.data.services.RetrofitClientService
import org.koin.core.module.Module
import org.koin.dsl.module



object DatabaseModule : () -> Module {
    override fun invoke(): Module = module  {
        single { RetrofitClientService.createService(ApiService::class.java)}
        single { ListingDataSourceFactory(get()) }

    }
}