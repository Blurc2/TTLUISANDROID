package peach.princess.my.net.ttluis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import peach.princess.my.net.ttluis.Utils.Constants
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK
import peach.princess.my.net.ttluis.domain.entity.Orden
import peach.princess.my.net.ttluis.ui.Base.BaseActivity
import peach.princess.my.net.ttluis.ui.login.LoginFragment

class LoginActivity : BaseActivity() {
    val scheduleProvider = AppScheduleProviderK()
    lateinit var navController: NavController
    lateinit var orden : Orden
    lateinit var url : String

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        navController= Navigation.findNavController(this,R.id.nav_host_fragment)
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

}
