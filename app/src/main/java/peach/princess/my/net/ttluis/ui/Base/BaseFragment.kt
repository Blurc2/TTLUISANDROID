package gshp.net.johnson.ui.base

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import peach.princess.my.net.ttluis.LoginActivity


/**
 * BaseDialog class, this must be override in all fragments
 * @author Itzae
 */
abstract class BaseFragment: Fragment() {
    //private var rootView: View? = null
    lateinit var activity: LoginActivity
    /**
     * Layout resource id for load fragment, this parameter must be override in fragment child
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Inflate view with the layout id override in child fragment, this method is not necessary
     * implement in this fragment.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.activity = (getActivity() as LoginActivity?)!!
    }


    fun showToastMsj(msj: String) {
        Toast.makeText(context, msj, Toast.LENGTH_SHORT).show()
    }


}