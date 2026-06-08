package com.kp.beautytips.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.model.CategoryModel
import com.kp.beautytips.model.SubCategoryModel
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView

class CategoryAdapter(
    categoryArrayList: ArrayList<CategoryModel>,
    private val onItemClick: OnItemClick
) : RecyclerView.Adapter<CategoryAdapter.HomeHolder>() {

    private var categoryArrayList = ArrayList<CategoryModel>()

    init {
        this.categoryArrayList = categoryArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val categoryModel = categoryArrayList[position]
        holder.cardView.setCardBackgroundColor(Color.parseColor(categoryArrayList[position].colorCode))
        holder.categoryName.text = categoryArrayList[position].categoryName
        holder.view.setBackgroundColor(Color.parseColor(categoryArrayList[position].viewColorCode))
        Glide.with(holder.itemView.context).load(categoryArrayList[position].image).into(holder.categoryImage)
        holder.itemView.setOnClickListener {
            onItemClick.onItemClick(
                categoryModel,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return categoryArrayList.size
    }

    interface OnItemClick {
        fun onItemClick(category: CategoryModel, position: Int)
    }

    inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView = itemView.findViewById(R.id.cardView)
        var categoryName: AppCompatTextView = itemView.findViewById(R.id.tvCategoryName)
        var view: View = itemView.findViewById(R.id.viewColor)
        var categoryImage = itemView.findViewById<CircularImageView>(R.id.imgCategory)!!
    }
}