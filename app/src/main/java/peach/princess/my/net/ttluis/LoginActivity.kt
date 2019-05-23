package peach.princess.my.net.ttluis

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import peach.princess.my.net.ttluis.Utils.Constants
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK
import peach.princess.my.net.ttluis.domain.Interactor.LoginInteractorImpl
import peach.princess.my.net.ttluis.domain.entity.Orden
import peach.princess.my.net.ttluis.ui.Base.BaseActivity
import peach.princess.my.net.ttluis.ui.login.LoginContract
import peach.princess.my.net.ttluis.ui.login.LoginFragment
import peach.princess.my.net.ttluis.ui.login.LoginViewModel

class LoginActivity : BaseActivity(),LoginContract.View {
    val scheduleProvider = AppScheduleProviderK()
    lateinit var navController: NavController
    var orden = MutableLiveData<Orden?>()
    var username = ""
    var usertype = ""
    lateinit var url : String

    private val viewModel by lazy {
        LoginViewModel(scheduleProvider, LoginInteractorImpl(),this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        orden.value = null
        navController= Navigation.findNavController(this,R.id.nav_host_fragment)
        viewModel.getUrl()

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FirebaseInstanceId", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.d("FirebaseInstanceId", token)
            })
    }

    override fun onBackPressed() {
        when(navController.currentDestination?.id)
        {
            R.id.loginFragment->{

            }
            R.id.mainFragment->{
                showCustomDialog(R.string.close_session_header,
                    getString(R.string.close_session_msg),
                    getString(R.string.dialog_no),
                    getString(R.string.dialog_yes),
                    Constants.Dialogs.DIALOG_NETWORK_AVAILABLE,
                    {}, {
                        FirebaseAuth.getInstance().signOut()
                        navController.popBackStack()
                    },
                    Constants.Dialogs.DIALOG_ACTION,
                    R.layout.dialog_generic
                )
            }
            else -> navController.popBackStack()
        }
    }

    override fun loginresult(flag: Boolean, msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUrlF(string: String) {
        Log.i("URLSITE",string)
        url = string
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
