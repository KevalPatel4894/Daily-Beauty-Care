package com.kp.beautytips.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.DetailsActivity
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.utils.AppUtils
import com.bumptech.glide.Glide


class ListAdapter(
    listArrayList: ArrayList<ListModel>,
    private val tabName: String
) : RecyclerView.Adapter<ListAdapter.HomeHolder>() {

    private var listArrayList = ArrayList<ListModel>()

    init {
        this.listArrayList = listArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.title.text = listArrayList[position].title
        if (listArrayList[position].descriptionName.isNotEmpty()) {
            holder.description.text = listArrayList[position].descriptionName
        } else {
            holder.description.visibility = View.GONE
        }
        Glide.with(holder.itemView.context).load(
            holder.itemView.context.getDrawable(
                listArrayList[position].image
            )
        ).into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra("tabName", tabName)
            intent.putExtra("title", listArrayList[position].title)
            intent.putExtra("image", listArrayList[position].image)
            intent.putExtra("details", listArrayList[position].details)
            it.context.startActivity(intent)
            AppUtils.startFromRightToLeft(it.context)
        }
    }

    override fun getItemCount(): Int {
        return listArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    open class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        var description: AppCompatTextView = itemView.findViewById(R.id.tvDescription)
        var image: AppCompatImageView = itemView.findViewById(R.id.imgSubCategory)
    }
}