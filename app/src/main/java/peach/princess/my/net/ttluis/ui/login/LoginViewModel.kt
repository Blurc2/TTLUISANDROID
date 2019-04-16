package peach.princess.my.net.ttluis.ui.login

import android.util.Log
import gshp.net.johnson.ui.base.BaseViewModel
import peach.princess.my.net.ttluis.domain.Interactor.LoginInteractor
import peach.princess.my.net.ttluis.domain.Repository.ScheduleProvider


class LoginViewModel(provider: ScheduleProvider, interactor: LoginInteractor, view: LoginContract.View) :
    BaseViewModel<LoginInteractor,LoginContract.View>(provider,view,interactor), LoginContract.ViewModel {
    override fun onDestroy() {
        onClean()
    }

    override fun login(correo: String, pass: String) {
        navigator?.showLoading()
        compositeDisposable?.add(
            interactor
                ?.login(correo, pass)
                ?.subscribeOn(scheduleProvider?.io())
                ?.observeOn(scheduleProvider?.ui())
            !!.subscribe({
                Log.i("Rxfirebase2", "User logged ${it.uid}")
                navigator?.hideLoading()
                navigator?.loginresult(true,it.uid)
            }, {
                navigator?.hideLoading()
                it.message?.let {
                    navigator?.loginresult(false,it)
                    Log.e("ERROR_DE:LOGIN", it)
                }

            })
        )
    }
}
