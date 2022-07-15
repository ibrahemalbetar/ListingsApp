package com.estarta.listingapp
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class Status {
    FIRST_LOADING,
    RUNNING,
    SUCCESS,
    FAILED,
    REFRESH,
    ALERT,
    NETWORK_ERROR
}

@Suppress("DataClassPrivateConstructor")
@Parcelize
data class RequestStatus private constructor(
    val status: Status,
    val msg: String? = null): Parcelable {
    companion object {
        val NETWORK_ERROR = RequestStatus(Status.NETWORK_ERROR)
        val SUCCESS_REFRESH = RequestStatus(Status.REFRESH)
        val FAILED_ALERT = RequestStatus(Status.ALERT)
        val INIT_LOADING = RequestStatus(Status.FIRST_LOADING)
        val LOADED = RequestStatus(Status.SUCCESS)
        val LOADING = RequestStatus(Status.RUNNING)
        val FAILED = RequestStatus(Status.FAILED)
        fun error(msg: String?) = RequestStatus(Status.FAILED, msg)
        fun error(status: Status, msg: String?) = RequestStatus(status, msg)
        fun success(status: Status, message: String?) = RequestStatus(status, message)

    }
}