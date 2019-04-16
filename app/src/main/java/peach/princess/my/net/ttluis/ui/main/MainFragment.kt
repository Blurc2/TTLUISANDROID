package peach.princess.my.net.ttluis.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.Utils.ProgressDialog
import peach.princess.my.net.ttluis.domain.Interactor.MainInteractorImpl
import peach.princess.my.net.ttluis.domain.entity.Orden
import peach.princess.my.net.ttluis.domain.entity.User
import peach.princess.my.net.ttluis.ui.main.adapter.OrderAdapter
import java.lang.Exception

class MainFragment : BaseFragment(),MainContract.View {

    override val layoutId: Int
        get() = R.layout.main_fragment

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by lazy {
        MainViewModel(activity.scheduleProvider,MainInteractorImpl(),this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvOrders.apply {
            adapter = OrderAdapter(ArrayList(),context)
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getOrderByUid(arguments!!.getString("uid"))
    }

    override fun loadData(user: User) {

        var orders = ArrayList<Orden>()
        orders.addAll(user.ordenes)
        orders.removeAt(0)
//        Log.i("MAINFRAGMENT", Gson().toJson(orders))
        (rvOrders.adapter as OrderAdapter).setData(orders)
    }

    override fun showLoading() = ProgressDialog.show(activity)

    override fun hideLoading() = ProgressDialog.dismiss()

}
