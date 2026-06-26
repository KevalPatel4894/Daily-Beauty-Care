package com.kp.beautytips.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kp.beautytips.R
import com.kp.beautytips.activity.DetailsActivity
import com.kp.beautytips.model.ListModel
import com.kp.beautytips.utils.AppUtils

class PersonalizedTipsAdapter(
    private val context: Context,
    private val tipsList: List<ListModel>,
    private val tagText: String
) : RecyclerView.Adapter<PersonalizedTipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_personalized_tip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tip = tipsList[position]

        holder.txtTipCategoryTag.text = tagText
        holder.txtTipTitle.text = tip.title
        holder.txtTipDuration.text = tip.descriptionName

        // Load Tip Thumbnail
        Glide.with(context)
            .load(tip.image)
            .placeholder(R.drawable.ic_book)
            .into(holder.imgTipThumbnail)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("tabName", tagText)
                putExtra("title", tip.title)
                putExtra("image", tip.image)
                putExtra("details", tip.details)
                putExtra("descriptionName", tip.descriptionName)
            }
            context.startActivity(intent)
            AppUtils.startFromRightToLeft(context)
        }
    }

    override fun getItemCount(): Int = tipsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgTipThumbnail: AppCompatImageView = itemView.findViewById(R.id.imgTipThumbnail)
        val txtTipCategoryTag: AppCompatTextView = itemView.findViewById(R.id.txtTipCategoryTag)
        val txtTipTitle: AppCompatTextView = itemView.findViewById(R.id.txtTipTitle)
        val txtTipDuration: AppCompatTextView = itemView.findViewById(R.id.txtTipDuration)
    }
}
