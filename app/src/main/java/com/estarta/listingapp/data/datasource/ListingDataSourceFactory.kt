package com.estarta.listingapp.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.data.services.ApiService

class ListingDataSourceFactory(
    private val apiService: ApiService,
) :
    DataSource.Factory<String, ListingResp.ListingItem>() {

    private lateinit var dataSource: ListingDataSource
    private val listingDataSourceLiveData = MutableLiveData<ListingDataSource>()

    override fun create(): DataSource<String, ListingResp.ListingItem> {

        dataSource =
            ListingDataSource(
                apiService = apiService
            )
        listingDataSourceLiveData.postValue(dataSource)
        return dataSource

    }

    fun getListingLiveDataSource(): MutableLiveData<ListingDataSource> {
        return listingDataSourceLiveData
    }


}
