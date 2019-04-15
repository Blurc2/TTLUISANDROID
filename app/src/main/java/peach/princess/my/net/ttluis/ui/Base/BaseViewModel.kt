package gshp.net.johnson.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import peach.princess.my.net.ttluis.domain.Repository.ScheduleProvider

open class BaseViewModel<I, N> : ViewModel  {
    var compositeDisposable: CompositeDisposable? = null
        private set
    var scheduleProvider: ScheduleProvider? = null
        private set
    var interactor: I? = null
        private set
    var navigator: N? = null

    constructor(scheduleProvider: ScheduleProvider, interactor: I) {
        this.scheduleProvider = scheduleProvider
        this.interactor = interactor
        compositeDisposable = CompositeDisposable()
    }

    constructor(scheduleProvider: ScheduleProvider, navigator: N, interactor: I) {
        this.scheduleProvider = scheduleProvider
        this.interactor = interactor
        this.navigator = navigator
        compositeDisposable = CompositeDisposable()
    }

    protected fun onClean() {
        compositeDisposable!!.dispose()
    }
}