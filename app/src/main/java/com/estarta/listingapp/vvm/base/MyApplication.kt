package com.estarta.listingapp.vvm.base
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.estarta.listingapp.injection.DatabaseModule
import com.estarta.listingapp.injection.RepositoriesModule
import com.estarta.listingapp.injection.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application(){

    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        setupKoin(this)
    }

    fun setupKoin(app: android.app.Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(
                    ViewModelsModule(),
                    RepositoriesModule(),
                    DatabaseModule()
                )
            )
        }
    }

}