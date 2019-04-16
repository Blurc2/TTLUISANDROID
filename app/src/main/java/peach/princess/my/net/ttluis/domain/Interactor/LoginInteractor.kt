package peach.princess.my.net.ttluis.domain.Interactor

import com.google.firebase.auth.FirebaseUser
import io.reactivex.Maybe

interface LoginInteractor
{
    fun login(correo: String, pass : String): Maybe<FirebaseUser>
}