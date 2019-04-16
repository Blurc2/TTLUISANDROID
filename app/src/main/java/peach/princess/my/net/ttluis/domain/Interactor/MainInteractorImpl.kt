package peach.princess.my.net.ttluis.domain.Interactor

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe
import io.reactivex.Observable
import peach.princess.my.net.ttluis.Utils.Constants.firebase.PATH_USER
import peach.princess.my.net.ttluis.domain.entity.User

class MainInteractorImpl : MainInteractor{
    override fun getOrdersByUid(uid: String): Maybe<DataSnapshot> {
        return RxFirebaseDatabase.observeSingleValueEvent(FirebaseDatabase.getInstance().reference.child(String.format(PATH_USER,uid)))
    }
}