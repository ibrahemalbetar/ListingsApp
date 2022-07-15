package com.estarta.listingapp.vvm.listings

import android.app.Application
import com.estarta.listingapp.data.repositories.listings.ListingsRepository
import com.estarta.listingapp.vvm.base.BaseViewModel

class ListingsViewModel(
    application: Application,
    private val listingsRepo: ListingsRepository,
) : BaseViewModel(application){
    val listingsList by lazy { listingsRepo.getListings() }
    val status by lazy { listingsRepo.getState() }


    fun refresh() {
        listingsRepo.refresh()
    }

    fun listIsEmpty(): Boolean {
        return listingsList?.value?.isEmpty() ?: true
    }

}