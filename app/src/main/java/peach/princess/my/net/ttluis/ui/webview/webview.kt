package peach.princess.my.net.ttluis.ui.webview

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_webview.*

import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.Utils.ProgressDialog


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



        webview.settings.javaScriptEnabled = true
        webview.settings.javaScriptCanOpenWindowsAutomatically = true

        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                if(arguments?.getInt("action") == 1)
                    view?.loadUrl("javascript:showRegisterModal()")
                hideLoading()
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                showLoading()
                return true
            }
        }

        webview.loadUrl(activity.url)
    }

    fun showLoading() = ProgressDialog.show(activity)

    fun hideLoading() = ProgressDialog.dismiss()
}