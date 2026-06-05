package com.kp.beautytips.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.SubCategoryActivity
import com.kp.beautytips.model.SubCategoryModel
import com.bumptech.glide.Glide

class SubCategoryAdapter(
    subCategoryArrayList: ArrayList<SubCategoryModel>,
    private val onItemClick: SubCategoryActivity
) : RecyclerView.Adapter<SubCategoryAdapter.HomeHolder>() {

    private var subCategoryArrayList = ArrayList<SubCategoryModel>()

    init {
        this.subCategoryArrayList = subCategoryArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sub_category, parent, false)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val subCategoryModel = subCategoryArrayList[position]
        holder.categoryName.text = subCategoryArrayList[position].categoryName
        Glide.with(holder.itemView.context).load(
            holder.itemView.context.getDrawable(
                subCategoryArrayList[position].image
            )
        ).into(holder.subCategoryImage)
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(
                subCategoryModel,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return subCategoryArrayList.size
    }

    interface OnItemClick {
        fun onItemClick(category: SubCategoryModel, position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName: AppCompatTextView = itemView.findViewById(R.id.tvCategoryName)
        var subCategoryImage: AppCompatImageView = itemView.findViewById(R.id.imgSubCategory)
    }
}