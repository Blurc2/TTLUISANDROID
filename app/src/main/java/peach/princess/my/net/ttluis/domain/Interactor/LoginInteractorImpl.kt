package peach.princess.my.net.ttluis.domain.Interactor

import android.R.attr.password
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Maybe


class LoginInteractorImpl : LoginInteractor {
    override fun login(correo: String, pass: String): Maybe<Any> {
        Log.i("LOGINDATA",correo)
        Log.i("LOGINDATA",pass)
        return RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), correo, pass)
            .map<Any> { authResult -> authResult.user != null }
    }
}