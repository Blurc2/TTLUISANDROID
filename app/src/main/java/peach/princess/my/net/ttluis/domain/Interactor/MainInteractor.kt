package peach.princess.my.net.ttluis.domain.Interactor

import com.google.firebase.database.DataSnapshot
import io.reactivex.Maybe
import io.reactivex.Observable
import peach.princess.my.net.ttluis.domain.entity.User

interface MainInteractor {

    fun getOrdersByUid(uid: String): Maybe<DataSnapshot>
}