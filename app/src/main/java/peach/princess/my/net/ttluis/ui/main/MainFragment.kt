package peach.princess.my.net.ttluis.ui.main

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.*
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.Utils.Constants
import peach.princess.my.net.ttluis.Utils.ProgressDialog
import peach.princess.my.net.ttluis.Utils.initRow
import peach.princess.my.net.ttluis.domain.Interactor.MainInteractorImpl
import peach.princess.my.net.ttluis.domain.entity.Orden
import peach.princess.my.net.ttluis.domain.entity.User
import peach.princess.my.net.ttluis.ui.main.adapter.OrderAdapter
import java.lang.IllegalStateException
import android.app.NotificationChannel
import android.os.Build.VERSION_CODES.O
import android.os.Build
import androidx.annotation.RequiresApi


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
                activity.navController.navigate(R.id.action_mainFragment_to_orderInfo)
                activity.orden.value = it

//                findNavController(this@MainFragment).navigate(R.id.action_mainFragment_to_orderFragment)
            }
        }

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.close_session -> {
//                    showToastMsj("Cerrar")
                    activity.showCustomDialog(R.string.close_session_header,
                        getString(R.string.close_session_msg),
                        getString(R.string.dialog_no),
                        getString(R.string.dialog_yes),
                        Constants.Dialogs.DIALOG_NETWORK_AVAILABLE,
                        {}, {
                            FirebaseAuth.getInstance().signOut()
                            findNavController(this).popBackStack()
                        },
                        Constants.Dialogs.DIALOG_ACTION,
                        R.layout.dialog_generic
                    )

                    true
                }

                R.id.web -> {
                    findNavController(this).navigate(R.id.action_mainFragment_to_webview2, bundleOf("action" to 2))

                    true
                }
                else -> {
                    showToastMsj("Else")
                    true
                }
            }
        }
        viewModel.orders.observe(this, Observer { ordenes ->
            Log.i("ORDENES",Gson().toJson(ordenes))
            (rvOrders.adapter as OrderAdapter).setData(ordenes)
        })

        viewModel.getOrderByUid(arguments!!.getString("uid"))


    }

    override fun loadData(user: User) {
        activity.username = "${user.nombre} ${user.ap} ${user.am}"
        activity.usertype = user.tipo
        val spannable1 = SpannableString(activity.username)
        val spannable2 = SpannableString(activity.usertype)
        spannable1.setSpan(RelativeSizeSpan(0.7F), 0, activity.username.length, 0)
        spannable2.setSpan(RelativeSizeSpan(0.7F), 0, activity.usertype.length, 0)
        spannable1.setSpan(ForegroundColorSpan(Color.WHITE), 0, activity.username.length, 0)
        spannable2.setSpan(ForegroundColorSpan(Color.WHITE), 0, activity.usertype.length, 0)
        activity.supportActionBar?.title = spannable1
        activity.supportActionBar?.subtitle = spannable2

        activity.orden.value?.let {orden ->
            val neworden = user.ordeneslist.find { it.nofolio == orden.nofolio}
            neworden?.let {
                Log.e("NewOrden",Gson().toJson(it))
                activity.orden.value = it
            }
        }
    }

    override fun shownotification(state: Int,id: Int) {
        Log.e("shownotification","shownotification $id -> $state")
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel("my_service", "My Background Service")
            } else {
                // If earlier version channel ID is not used
                // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                ""
            }

        val msg = when(state){
            0 -> "Asignada, en espera de ser resuelta"
            1 -> "Resuelta"
            2 -> "No se pudo resolver"
            else -> "Sin asignar"
        }

        val bigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle("Orden $id actualizada")
        bigTextStyle.bigText("El estado de su orden con No. Folio $id cambio a $msg")

        context?.let {
            val notificationBuilder = NotificationCompat.Builder(context!!, channelId )
            val notification = notificationBuilder
                .setSmallIcon(R.drawable.escudoescom)
                .setStyle(bigTextStyle)
                .setCategory(Notification.CATEGORY_EVENT)
                .build()
            val notificationManager = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify("tag", 101, notification)
        }

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_HIGH)
        chan.lightColor = R.color.colorPrimary
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = activity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }


    override fun showLoading() = ProgressDialog.show(activity)

    override fun hideLoading() = ProgressDialog.dismiss()

}
