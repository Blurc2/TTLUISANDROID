package peach.princess.my.net.ttluis.ui.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import peach.princess.my.net.ttluis.R
import peach.princess.my.net.ttluis.domain.entity.Orden


class OrderAdapter(val items : ArrayList<Orden>, val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {


    fun setData(list : ArrayList<Orden>)
    {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("OrderAdapter","Item -> ${Gson().toJson(items.get(position))}")
        holder.tvfolio.text = "No. Folio : ${items.get(position).nofolio}"
        holder.tvstart.text = "Fecha de inicio : ${items.get(position).start.split(",")[0]}"
        holder.tvend.text = if(items.get(position).end != "") "Fecha de fin : ${items.get(position).end.split(",")[0]}" else "--"
        holder.tvubicacion.text = if(items.get(position).subdepto != null) "Ubicación : ${items.get(position).subdepto?.ubicacion}" else "Ubicación : ${items.get(position).depto.ubicacion}"
        holder.tvestado.text = when(items.get(position).estado){
            -1 -> "En proceso de ser asignada"
            0 -> "Asignada, en proceso de ser resuelta"
            1 -> "Resuelta"
            else -> "En proceso de ser asignada"
        }
        holder.tvfolio.background = when(items.get(position).estado){
            -1 -> context.getDrawable(R.color.nosendColor)
            0 -> context.getDrawable(R.color.tosendColor)
            1 -> context.getDrawable(R.color.sendColor)
            else -> context.getDrawable(R.color.nosendColor)
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvfolio : TextView = view.findViewById(R.id.folio)
        val tvstart : TextView = view.findViewById(R.id.start)
        val tvend : TextView = view.findViewById(R.id.end)
        val tvubicacion : TextView = view.findViewById(R.id.ubicacion)
        val tvestado : TextView = view.findViewById(R.id.estado)
    }
}

