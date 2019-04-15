package peach.princess.my.net.ttluis.domain.Interactor

import io.reactivex.Maybe

interface LoginInteractor
{
    fun login(correo: String, pass : String): Maybe<Any>
}