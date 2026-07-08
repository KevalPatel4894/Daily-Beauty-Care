package com.kp.beautytips.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.model.CustomTip

class CustomTipsAdapter(
    private var tipsList: List<CustomTip>,
    private val onItemClick: (CustomTip) -> Unit
) : RecyclerView.Adapter<CustomTipsAdapter.TipHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_tip, parent, false)
        return TipHolder(view)
    }

    override fun onBindViewHolder(holder: TipHolder, position: Int) {
        val tip = tipsList[position]
        holder.tvTitle.text = tip.title
        holder.tvCategory.text = tip.category
        
        if (tip.duration.isNotEmpty()) {
            holder.tvDuration.text = tip.duration
            holder.tvDuration.visibility = View.VISIBLE
        } else {
            holder.tvDuration.visibility = View.GONE
        }

        // Set category specific icon
        val iconRes = when (tip.category) {
            "Face", "Gesicht", "Cara", "Visage", "चेहरा", "顔", "얼굴", "Rosto", "Лицо" -> R.drawable.ic_face
            "Hair", "Haare", "Cabello", "Cheveux", "बाल", "髪", "헤어", "Cabelo", "Волосы" -> R.drawable.ic_hair
            "Skin", "Haut", "Piel", "Peau", "त्वचा", "肌", "피부", "Pele", "Кожа" -> R.drawable.ic_skin
            "Eyes", "Augen", "Ojos", "Yeux", "आंखें", "目", "눈", "Olhos", "Глаза" -> R.drawable.beautifuleyes_title
            "Hands & Feet", "Hands &amp; Feet", "Hände & Füße", "Manos y pies", "Mains & Pieds", "हाथ और पैर", "手と足", "손 & 발", "Mãos & Pés", "Руки и ноги" -> R.drawable.ic_hand_feet
            else -> R.drawable.ic_book
        }
        
        holder.imgCategoryIcon.setImageResource(iconRes)
        
        holder.itemView.setOnClickListener {
            onItemClick(tip)
        }
    }

    override fun getItemCount(): Int {
        return tipsList.size
    }

    fun updateList(newList: List<CustomTip>) {
        tipsList = newList
        notifyDataSetChanged()
    }

    class TipHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: AppCompatTextView = itemView.findViewById(R.id.tvCategory)
        val tvDuration: AppCompatTextView = itemView.findViewById(R.id.tvDuration)
        val imgCategoryIcon: AppCompatImageView = itemView.findViewById(R.id.imgCategoryIcon)
    }
}
