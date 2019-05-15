package peach.princess.my.net.ttluis.ui.login

interface LoginContract {
    interface View {
        fun loginresult(flag: Boolean,msg: String)
        fun setUrlF(string: String)
        fun showLoading()
        fun hideLoading()
    }

    interface ViewModel {
        fun onDestroy()
        fun login(correo: String, pass: String)
        fun getUrl()
    }
}