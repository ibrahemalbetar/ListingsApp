package com.ltm.istartarabic.data.services

import android.os.Message
import android.os.Parcelable
import io.reactivex.observers.DisposableObserver
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException


abstract class ApiCallback<T: Any> : DisposableObserver<T>() {

    override fun onNext(t: T) {
        onSuccess(t)
        onFinish()

    }

    override fun onComplete() {

    }


    override fun onError(e: Throwable) {

        e.let {

            when (e) {
                is HttpException -> {
                    onFinish()
                    handleError(getErrorMessage(e.response()?.errorBody()!!.string()))
                }
                is SocketTimeoutException -> {
                    onFinish()
                    timeoutException(e)
                }
                is IOException -> {
                    onFinish()
                    networkException(e)
                }
                else -> {
                    onFinish()
                    unKnownException(e)
                }


            }

        }


    }


    abstract fun onSuccess(data: T)
    abstract fun handleError(error: ErrorResp)

    abstract fun timeoutException(t: Exception)
    abstract fun networkException(t: Exception)
    abstract fun unKnownException(t: Throwable)
    abstract fun onFinish()


    private fun getErrorMessage(errorBody: String): ErrorResp {
        return try {
            val jsonObject = JSONObject(errorBody)
            ErrorResp(jsonObject.getJSONObject("error").getString("code"), jsonObject.getJSONObject("error").getString("message"))
        }
        catch (e:Exception){
            ErrorResp("Unknown", "")
        }
    }


    @Parcelize
    data class ErrorResp(var code:String?="", var message: String?=""):Parcelable
}