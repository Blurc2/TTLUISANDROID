package peach.princess.my.net.ttluis.domain.Interactor

import android.R.attr.password
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Maybe


class LoginInteractorImpl : LoginInteractor {
    override fun login(correo: String, pass: String): Maybe<FirebaseUser> {
        Log.i("LOGINDATA",correo)
        Log.i("LOGINDATA",pass)
        return RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), correo, pass)
            .map { authResult -> authResult.user }
    }
}