package peach.princess.my.net.ttluis.ui.login

interface LoginContract {
    interface View {
        fun loginresult(msg: String)
        fun showLoading()
        fun hideLoading()
    }

    interface ViewModel {
        fun onDestroy()
        fun login(correo: String, pass: String)
    }
}