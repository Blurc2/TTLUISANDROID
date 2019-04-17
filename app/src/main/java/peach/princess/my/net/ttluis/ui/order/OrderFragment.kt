package peach.princess.my.net.ttluis.ui.order


import android.os.Bundle
import android.app.Fragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.order_info.*

import peach.princess.my.net.ttluis.R

/**
 * A simple [Fragment] subclass.
 *
 */
class OrderFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.order_info

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close_vd_theme_24px)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }



}
