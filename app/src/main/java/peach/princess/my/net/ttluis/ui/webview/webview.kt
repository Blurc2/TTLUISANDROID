package peach.princess.my.net.ttluis.ui.webview

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_webview.*

import peach.princess.my.net.ttluis.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [webview.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [webview.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class webview : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_webview

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webview.loadUrl(activity.url)
        webview.settings.javaScriptEnabled = true
    }
}