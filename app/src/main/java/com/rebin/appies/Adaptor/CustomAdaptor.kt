package com.rebin.appies.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebin.appies.R
import com.rebin.appies.network.DataObject
import com.rebin.appies.network.Heirarchy
import com.rebin.appies.ui.MainActivity
import java.util.zip.Inflater

class CustomAdaptor(var dataObject: List<Heirarchy>?, var clickposition: MainActivity) :RecyclerView.Adapter<CustomAdaptor.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        var tvname:TextView
        var tvdesignation:TextView
        var ivphone:ImageView
        var ivmsg:ImageView
        init {
            tvname=view.findViewById(R.id.tvname)
            tvdesignation=view.findViewById(R.id.tvdesignation)
            ivmsg=view.findViewById(R.id.ivmsg)
            ivphone=view.findViewById(R.id.ivphone)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdaptor.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdaptor.ViewHolder, position: Int) {
        holder.tvname.setText(dataObject!!.get(position).contactName)
        holder.tvdesignation.setText(dataObject!!.get(position).designationName)
        holder.ivphone.setOnClickListener {

            clickposition.phonecall(dataObject!!.get(position).contactNumber)
        }
        holder.ivmsg.setOnClickListener {
            clickposition.message(dataObject!!.get(position).contactNumber)

        }

    }

    override fun getItemCount(): Int {
        return dataObject!!.size
    }
}