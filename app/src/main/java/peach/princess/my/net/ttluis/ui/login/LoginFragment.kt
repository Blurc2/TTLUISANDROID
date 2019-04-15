package peach.princess.my.net.ttluis.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.DIALOG_NETWORK_AVAILABLE
import peach.princess.my.net.ttluis.Utils.Constants.Dialogs.DIALOG_SIMPLE
import peach.princess.my.net.ttluis.Utils.ProgressDialog
import peach.princess.my.net.ttluis.Utils.Validation
import peach.princess.my.net.ttluis.data.executor.AppScheduleProviderK
import peach.princess.my.net.ttluis.domain.Interactor.LoginInteractorImpl

class LoginFragment : BaseFragment(),LoginContract.View {
    private val scheduleProvider = AppScheduleProviderK()

    override val layoutId: Int
        get() = R.layout.login_fragment


    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel by lazy {
        LoginViewModel(scheduleProvider,LoginInteractorImpl(),this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnLogin.setOnClickListener{
            verifyNetwork()
        }
    }

    private fun verifyNetwork() {
        if (!Validation.isOnline()) {
            activity.showCustomDialog(R.string.label_error,
                getString(R.string.network_error),
                getString(R.string.dialog_empty),
                getString(R.string.dialog_accept),
                DIALOG_NETWORK_AVAILABLE,
                {}, {},
                DIALOG_SIMPLE,
                R.layout.dialog_generic
            )
        } else {
            login(ETCorreo.text.toString(), ETPass.text.toString())
        }
    }

    private fun login(user: String, password: String) {
        val userError = Validation.getEmptyError(user)
        val pwdError = Validation.getEmptyError(password)

        if ((userError > 0) && (pwdError > 0)) {
            passLayout.isPasswordVisibilityToggleEnabled = false
            ETCorreo.error = getString(R.string.error_field_required)
            ETPass.error = getString(R.string.error_field_required)
        } else if (userError > 0) {
            passLayout.isPasswordVisibilityToggleEnabled = false
            ETCorreo.error = getString(R.string.error_field_required)
        } else if (pwdError > 0) {
            passLayout.isPasswordVisibilityToggleEnabled = false
            ETPass.error = getString(R.string.error_field_required)
        } else {
            viewModel.login(user, password)
        }
    }

    override fun loginresult(msg: String) {
        if(msg.isNotEmpty())
        activity.showCustomDialog(R.string.label_login_result,
            msg,
            getString(R.string.dialog_empty),
            getString(R.string.dialog_accept),
            DIALOG_NETWORK_AVAILABLE,
            {}, {},
            DIALOG_SIMPLE,
            R.layout.dialog_generic
        )
        else
            findNavController(this).navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun showLoading() = ProgressDialog.show(activity)

    override fun hideLoading() = ProgressDialog.dismiss()
}