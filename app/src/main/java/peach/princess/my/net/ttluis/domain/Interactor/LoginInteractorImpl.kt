package peach.princess.my.net.ttluis.domain.Interactor

import android.R.attr.password
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Maybe
import peach.princess.my.net.ttluis.Utils.Constants


class LoginInteractorImpl : LoginInteractor {
    override fun login(correo: String, pass: String): Maybe<FirebaseUser> {
        Log.i("LOGINDATA",correo)
        Log.i("LOGINDATA",pass)
        return RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), correo, pass)
            .map { authResult -> authResult.user }
    }

    override fun getUrl(): Maybe<DataSnapshot> {
        return RxFirebaseDatabase.observeSingleValueEvent(FirebaseDatabase.getInstance().reference.child(Constants.firebase.PATH_URL))
    }
}