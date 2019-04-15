package peach.princess.my.net.ttluis.data.executor

import android.os.HandlerThread
import android.util.Log
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import peach.princess.my.net.ttluis.domain.Repository.ScheduleProvider
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppScheduleProviderK : ScheduleProvider {

    private val executor: ExecutorService
    private val t: HandlerThread

    init {
        val nCores = Runtime.getRuntime().availableProcessors() + 1
        Log.i("Construtor", "AppSchedulerProvider:  n-Cores: $nCores")
        executor = Executors.newFixedThreadPool(nCores)
        t = HandlerThread("worker")
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.from(executor)
    }

    override fun lo(): Scheduler {
        if (!t.isAlive)
            t.start()
        return AndroidSchedulers.from(t.looper)
    }

}