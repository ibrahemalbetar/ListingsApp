package com.estarta.listingapp.data.repositories.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.estarta.listingapp.RequestStatus
import com.estarta.listingapp.data.datasource.ListingDataSource
import com.estarta.listingapp.data.datasource.ListingDataSourceFactory
import com.estarta.listingapp.data.models.ListingResp

interface ListingsRepository {

    fun getListings(): LiveData<PagedList<ListingResp.ListingItem>>?
    fun getState(): LiveData<RequestStatus>
    fun refresh()


    companion object : (ListingDataSourceFactory) -> ListingsRepository {
        override fun invoke(
            listingsDataSourceFactory: ListingDataSourceFactory
        ): ListingsRepository = ListingsRepositoryImpl(listingsDataSourceFactory)
    }

}

class ListingsRepositoryImpl(private val listingDataFactory: ListingDataSourceFactory) : ListingsRepository {

    private var listingsPagedList: LiveData<PagedList<ListingResp.ListingItem>>? = null

    init {

        val config =
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                //.setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
                .build()

        listingsPagedList = (LivePagedListBuilder(listingDataFactory, config)).build()
    }

    override fun getListings(): LiveData<PagedList<ListingResp.ListingItem>>? {
        return listingsPagedList
    }

    override fun getState(): LiveData<RequestStatus> = Transformations.switchMap(
        listingDataFactory.getListingLiveDataSource(), ListingDataSource::state
    )

    override fun refresh() {
        listingDataFactory.getListingLiveDataSource().value?.invalidate()
    }

}
