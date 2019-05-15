package peach.princess.my.net.ttluis.domain.Interactor

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import io.reactivex.Maybe

interface LoginInteractor
{
    fun login(correo: String, pass : String): Maybe<FirebaseUser>
    fun getUrl(): Maybe<DataSnapshot>
}