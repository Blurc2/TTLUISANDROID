package peach.princess.my.net.ttluis.ui.Base

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import peach.princess.my.net.ttluis.Utils.Constants
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK
import peach.princess.my.net.ttluis.data.network.services.ApiService
import peach.princess.my.net.ttluis.data.network.services.TrustOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
        lateinit var firebaseDatabase: FirebaseDatabase
    }
    lateinit var appScheduleProvider: AppScheduleProviderK
    private val apiClientConfig by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.Routes.URL)
            .client(TrustOkHttpClient.getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val apiService  by lazy { ApiService(apiClientConfig) }
    /**
     * Create and save instance of applications
     */
    override fun onCreate() {
        super.onCreate()
        appScheduleProvider = AppScheduleProviderK()
        instance = this
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setPersistenceEnabled(true)
        FirebaseDatabase.getInstance().getReference(Constants.firebase.PATH_USERS).keepSynced(true)
    }

}