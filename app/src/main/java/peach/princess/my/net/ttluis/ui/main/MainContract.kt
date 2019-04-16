package peach.princess.my.net.ttluis.ui.main

import peach.princess.my.net.ttluis.domain.entity.User

interface MainContract {
    interface View {
        fun loadData(user : User)
        fun showLoading()
        fun hideLoading()
    }

    interface ViewModel {
        fun onDestroy()
        fun getOrderByUid(uid: String)
    }
}