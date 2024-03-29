package peach.princess.my.net.ttluis.ui.login

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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

    override fun getUrl(){
        compositeDisposable?.add(
            interactor
                ?.getUrl()
                ?.subscribeOn(scheduleProvider?.io())
                ?.observeOn(scheduleProvider?.ui())
            !!.subscribe({

                it.ref.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        p0?.let {
                            var url = p0.getValue(String::class.java)
                            url?.let {
                                navigator?.setUrlF(it)
                            }


                        }
                    }

                })
            }, {
                it.message?.let {
                    Log.e("ERROR_DE:LOGIN", it)
                }

            })
        )
    }
}
