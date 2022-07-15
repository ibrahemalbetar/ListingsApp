package com.estarta.listingapp.data.services

import com.estarta.listingapp.data.models.ListingResp
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("default/dynamodb-writer")
    fun listingList(
    ): Observable<ListingResp>

}