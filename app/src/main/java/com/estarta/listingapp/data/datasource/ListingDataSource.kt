package com.estarta.listingapp.data.datasource

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.estarta.listingapp.RequestStatus
import com.estarta.listingapp.data.models.ListingResp
import com.estarta.listingapp.data.services.ApiService
import com.estarta.listingapp.extensions.isInt
import com.ltm.istartarabic.data.services.ApiCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListingDataSource(
    private val apiService: ApiService,
) :
    PageKeyedDataSource<String, ListingResp.ListingItem>() {


    var state: MutableLiveData<RequestStatus> = MutableLiveData()

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, ListingResp.ListingItem>
    ) {
        //  updateState(true, RequestStatus.LOADING)

        apiService.listingList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ApiCallback<ListingResp>() {
                override fun onSuccess(data: ListingResp) {

                    updateState(true, RequestStatus.LOADED)

                    var i: Int? = null

                    if (params.key.isInt() && params.key.toInt() > 1) {
                        i = params.key.toInt() + 1
                    }

                    data.results?.let {

                        callback.onResult(it, i?.toString()) }

                }

                override fun handleError(error: ErrorResp) {
                    updateState(true, RequestStatus.error(error.message))

                }

                override fun timeoutException(t: Exception) {
                    updateState(true, RequestStatus.error(t.message))


                }

                override fun networkException(t: Exception) {
                    updateState(true, RequestStatus.NETWORK_ERROR)

                }

                override fun unKnownException(t: Throwable) {
                    updateState(true, RequestStatus.error(t.message))

                }

                override fun onFinish() {
                    //successLogin.postValue(false)
                }

            })

    }
    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, ListingResp.ListingItem>
    ) {
    }
    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, ListingResp.ListingItem>
    ) {

        updateState(true, RequestStatus.LOADING)

        apiService.listingList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : ApiCallback<ListingResp>() {
                override fun onSuccess(data: ListingResp) {

                    data.results?.let {

                        callback.onResult(it, null, "0")
                        updateState(true, RequestStatus.LOADED)
                    }

                    if (data.results == null || data.results.isNullOrEmpty()) {
                        updateState(true, RequestStatus.FAILED)
                    }


                }

                override fun handleError(error: ErrorResp) {
                    updateState(true, RequestStatus.error(error.message))

                }

                override fun timeoutException(t: Exception) {
                    updateState(true, RequestStatus.error(t.message))


                }

                override fun networkException(t: Exception) {
                    updateState(true, RequestStatus.NETWORK_ERROR)

                }

                override fun unKnownException(t: Throwable) {
                    updateState(true, RequestStatus.error(t.message))

                }

                override fun onFinish() {
                }

            })
    }


    private fun updateState(isLoading: Boolean = true, state: RequestStatus) {
        if (!isLoading) {
            return
        }
        this.state.postValue(state)
    }


}
