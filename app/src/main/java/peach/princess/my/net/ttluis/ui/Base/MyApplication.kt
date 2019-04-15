package peach.princess.my.net.ttluis.ui.Base

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
        lateinit var firebaseDatabase: FirebaseDatabase
    }
    lateinit var appScheduleProvider: AppScheduleProviderK
    /**
     * Create and save instance of applications
     */
    override fun onCreate() {
        super.onCreate()
        appScheduleProvider = AppScheduleProviderK()
        instance = this
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.setPersistenceEnabled(true)
    }

}