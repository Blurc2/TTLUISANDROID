package peach.princess.my.net.ttluis.ui.order_info

import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import gshp.net.johnson.ui.base.BaseFragment
import kotlinx.android.synthetic.main.order_info_fragment.*
import peach.princess.my.net.ttluis.R

class OrderInfo  : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.order_info_fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i("OrdenINFO",Gson().toJson(activity.orden))
        with(activity.orden)
        {
            when(estado)
            {
                -1-> {
                    tec.text = "Sin asignar"
                    header.setBackgroundColor(ContextCompat.getColor(context!!, R.color.unknownColor))
                    val spannable1 = SpannableString( "Estado\nSin asignar")
                    spannable1.setSpan(RelativeSizeSpan(0.6F), 0, ("Estado\nSin asignar").indexOf("\n"), 0)
                    state.text =spannable1
                    state.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_power_settings, 0, 0, 0)
                    obsdoc.text = descripcioneslist[0]
                    obstec.text = "Sin Observaciones"
                }
                0-> {
                    tec.text = tecnico
                    header.setBackgroundColor(ContextCompat.getColor(context!!, R.color.tosendColor))
                    val spannable1 = SpannableString( "Estado \nAsignada, en espera de ser resuelta")
                    spannable1.setSpan(RelativeSizeSpan(0.6F), 0, ("Estado \nAsignada, en espera de ser resuelta").indexOf("\n"), 0)
                    state.text = spannable1
                    state.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_complain, 0, 0, 0)
                    obsdoc.text = descripcioneslist[0]
                    obstec.text = "Sin Observaciones"
                }
                1-> {
                    tec.text = tecnico
                    header.setBackgroundColor(ContextCompat.getColor(context!!, R.color.sendColor))
                    val spannable1 = SpannableString( "Estado \nResuelta")
                    spannable1.setSpan(RelativeSizeSpan(0.6F), 0, ("Estado \nResuelta").indexOf("\n"), 0)
                    state.text = spannable1
                    state.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_maps_and_flags, 0, 0, 0)
                    obsdoc.text = descripcioneslist[0]
                    obstec.text = descripcioneslist[1]
                }
                2->{
                    tec.text = tecnico
                    header.setBackgroundColor(ContextCompat.getColor(context!!, R.color.nosendColor))
                    val spannable1 = SpannableString( "Estado \nNo se pudo resolver")
                    spannable1.setSpan(RelativeSizeSpan(0.6F), 0, ("Estado \nNo se pudo resolver").indexOf("\n"), 0)
                    state.text = spannable1
                    state.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_cancel, 0, 0, 0)
                    obsdoc.text = descripcioneslist[0]
                    obstec.text = descripcioneslist[1]
                }
                else-> "Sin asignar"
            }

            dept.text = depto.nombre

            val spannable2 = SpannableString( "Fecha de inicio \n$start")
            spannable2.setSpan(RelativeSizeSpan(0.6F), 0, ("Fecha de inicio \n$start").indexOf("\n"), 0)
            ini.text = spannable2

            val spannable4 = SpannableString( "Tipo de trabajo \n$trabajo")
            spannable4.setSpan(RelativeSizeSpan(0.6F), 0, ("Tipo de trabajo \n$trabajo").indexOf("\n"), 0)
            work.text = spannable4

            val spannable5 = SpannableString( "Equipo\n$equipo")
            spannable5.setSpan(RelativeSizeSpan(0.6F), 0, ("Equipo\n$equipo").indexOf("\n"), 0)
            equip.text = spannable5


            if(end.isEmpty())
            {
                val spannable3 = SpannableString( "Fecha de fin \nSin finalizar")
                spannable3.setSpan(RelativeSizeSpan(0.6F), 0, ("Fecha de fin \nSin finalizar").indexOf("\n"), 0)
                fin.text = spannable3
            }
            else
            {
                val spannable3 = SpannableString( "Fecha de fin \n$end")
                spannable3.setSpan(RelativeSizeSpan(0.6F), 0, ("Fecha de fin \n$end").indexOf("\n"), 0)
                fin.text = spannable3
            }

            orderfolio.text = String.format(orderfolio.text.toString(),nofolio.toString())

            doc.text = docente

            edifdepto.text = depto.ubicacion.substring(0,depto.ubicacion.indexOf("Piso"))
            pisodepto.text = depto.ubicacion.substring(depto.ubicacion.indexOf("Piso"),depto.ubicacion.indexOf("sala"))
            saladepto.text = depto.ubicacion.substring(depto.ubicacion.indexOf("sala"),depto.ubicacion.length)

            if(subdepto != null)
            {
                subdept.text = subdepto!!.nombre
                edifsub.text = subdepto!!.ubicacion.substring(0,subdepto!!.ubicacion.indexOf("Piso"))
                pisosub.text = subdepto!!.ubicacion.substring(subdepto!!.ubicacion.indexOf("Piso"),subdepto!!.ubicacion.indexOf("sala"))
                salasub.text = subdepto!!.ubicacion.substring(subdepto!!.ubicacion.indexOf("sala"),subdepto!!.ubicacion.length)
            }
            else
            {
                subdept.text = "Sin subdepartamento"
                edifsub.text = "Sin subdepartamento"
                pisosub.text = "Sin subdepartamento"
                salasub.text = "Sin subdepartamento"
            }

        }

    }
}
