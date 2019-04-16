package peach.princess.my.net.ttluis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK
import peach.princess.my.net.ttluis.ui.Base.BaseActivity
import peach.princess.my.net.ttluis.ui.login.LoginFragment

class LoginActivity : BaseActivity() {
    val scheduleProvider = AppScheduleProviderK()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }

}
