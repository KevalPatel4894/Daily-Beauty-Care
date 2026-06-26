package com.kp.beautytips.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kp.beautytips.R
import com.kp.beautytips.model.DiaryEntry
import java.io.File

class DiaryAdapter(
    private val context: Context,
    private val entries: List<DiaryEntry>,
    private val onItemClick: (DiaryEntry) -> Unit
) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        return DiaryViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_diary, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val entry = entries[position]

        holder.txtDiaryDate.text = entry.date
        holder.txtDiaryTitle.text = entry.title
        holder.txtDiarySnippet.text = entry.content

        // Photo Log Thumbnail
        if (!entry.imagePath.isNullOrEmpty()) {
            val imgFile = File(entry.imagePath)
            if (imgFile.exists()) {
                holder.cardDiaryThumbnail.visibility = View.VISIBLE
                Glide.with(context)
                    .load(imgFile)
                    .placeholder(R.drawable.ic_book)
                    .error(R.drawable.ic_book)
                    .into(holder.imgDiaryThumbnail)
            } else {
                holder.cardDiaryThumbnail.visibility = View.GONE
            }
        } else {
            holder.cardDiaryThumbnail.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            onItemClick(entry)
        }
    }

    override fun getItemCount(): Int = entries.size

    class DiaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardDiaryThumbnail: CardView = itemView.findViewById(R.id.cardDiaryThumbnail)
        val imgDiaryThumbnail: AppCompatImageView = itemView.findViewById(R.id.imgDiaryThumbnail)
        val txtDiaryDate: AppCompatTextView = itemView.findViewById(R.id.txtDiaryDate)
        val txtDiaryTitle: AppCompatTextView = itemView.findViewById(R.id.txtDiaryTitle)
        val txtDiarySnippet: AppCompatTextView = itemView.findViewById(R.id.txtDiarySnippet)
    }
}
