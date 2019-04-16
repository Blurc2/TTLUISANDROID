package peach.princess.my.net.ttluis.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import gshp.net.johnson.ui.base.BaseViewModel
import peach.princess.my.net.ttluis.domain.Interactor.MainInteractor
import peach.princess.my.net.ttluis.domain.Repository.ScheduleProvider
import peach.princess.my.net.ttluis.domain.entity.User


class MainViewModel(provider: ScheduleProvider, interactor: MainInteractor, view: MainContract.View) :
    BaseViewModel<MainInteractor, MainContract.View>(provider,view,interactor), MainContract.ViewModel {
    override fun onDestroy() {
        onClean()
    }

    override fun getOrderByUid(uid: String) {
        navigator?.showLoading()
        compositeDisposable?.add(
            interactor
                ?.getOrdersByUid(uid)
                ?.subscribeOn(scheduleProvider?.io())
                ?.observeOn(scheduleProvider?.ui())
            !!.subscribe({

                it.ref.addValueEventListener(object : ValueEventListener{
                    override fun onCancelled(p0: DatabaseError) {
                    }

                    override fun onDataChange(p0: DataSnapshot) {
                        p0?.let {
                            navigator?.loadData(p0.getValue(User::class.java) as User)
                        }
                    }

                })
                navigator?.hideLoading()

            }, {
                navigator?.hideLoading()
                it.message?.let {
                    Log.e("ERROR_DE:LOGIN", it)
                }

            })
        )
    }

}
