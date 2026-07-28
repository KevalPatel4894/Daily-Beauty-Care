package com.kp.beautytips.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.model.SeasonalTipModel

class SeasonalAdapter(
    private var tips: List<SeasonalTipModel>
) : RecyclerView.Adapter<SeasonalAdapter.ViewHolder>() {

    fun updateData(newTips: List<SeasonalTipModel>) {
        this.tips = newTips
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_seasonal_tip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tips[position]
        holder.txtTitle.text = item.title
        holder.txtDesc.text = item.description
    }

    override fun getItemCount(): Int = tips.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtTipTitle)
        val txtDesc: TextView = itemView.findViewById(R.id.txtTipDesc)
    }
}
