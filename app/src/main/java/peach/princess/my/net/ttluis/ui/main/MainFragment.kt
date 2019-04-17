package peach.princess.my.net.ttluis.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.order_info.*
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.Utils.ProgressDialog
import peach.princess.my.net.ttluis.Utils.initRow
import peach.princess.my.net.ttluis.domain.Interactor.MainInteractorImpl
import peach.princess.my.net.ttluis.domain.entity.Orden
import peach.princess.my.net.ttluis.domain.entity.User
import peach.princess.my.net.ttluis.ui.main.adapter.OrderAdapter

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
            initRow(spacingItem = 10)
            adapter = OrderAdapter(ArrayList(),context){
                findNavController(this@MainFragment).navigate(R.id.action_mainFragment_to_orderFragment)
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.close_session -> {
//                    showToastMsj("Cerrar")
                    FirebaseAuth.getInstance().signOut()
                    findNavController(this).popBackStack()
                    true
                }
                else -> {
                    showToastMsj("Else")
                    true
                }
            }
        }
        viewModel.getOrderByUid(arguments!!.getString("uid"))
    }

    override fun loadData(user: User) {
        Log.i("MAINFRAGMENT", Gson().toJson(user.ordeneslist))
        (rvOrders.adapter as OrderAdapter).setData(user.ordeneslist)
    }

    override fun showLoading() = ProgressDialog.show(activity)

    override fun hideLoading() = ProgressDialog.dismiss()

}
