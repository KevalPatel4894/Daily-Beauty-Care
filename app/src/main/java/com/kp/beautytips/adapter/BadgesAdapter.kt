package com.kp.beautytips.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.BadgeModel

class BadgesAdapter(
    private val context: Context,
    private val badges: List<BadgeModel>
) : RecyclerView.Adapter<BadgesAdapter.BadgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeViewHolder {
        return BadgeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_badge, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BadgeViewHolder, position: Int) {
        val badge = badges[position]

        // 1. Text & Description
        holder.txtBadgeName.text = context.getString(badge.titleRes)
        holder.txtBadgeDesc.text = context.getString(badge.descRes)

        // 2. Progress
        val progressPercent = if (badge.maxProgress > 0) {
            (badge.currentProgress.toFloat() / badge.maxProgress.toFloat() * 100).toInt().coerceIn(0, 100)
        } else {
            0
        }
        holder.progressBadge.progress = progressPercent
        holder.txtBadgeProgress.text = String.format(
            context.getString(R.string.badge_progress_format),
            badge.currentProgress.coerceAtMost(badge.maxProgress),
            badge.maxProgress
        )

        // 3. Status Tag & Lock visuals
        if (badge.isUnlocked) {
            holder.imgBadgeIcon.alpha = 1.0f
            holder.imgBadgeIcon.setColorFilter(Color.parseColor(badge.tintColor))
            holder.imgBadgeLock.visibility = View.GONE
            
            holder.txtBadgeStatus.text = context.getString(R.string.badge_unlocked)
            holder.txtBadgeStatus.setBackgroundColor(Color.parseColor("#E8F5E9")) // Light Green
            holder.txtBadgeStatus.setTextColor(Color.parseColor("#2E7D32")) // Dark Green
        } else {
            holder.imgBadgeIcon.alpha = 0.4f
            holder.imgBadgeIcon.setColorFilter(Color.parseColor("#CCCCCC")) // Grey out
            holder.imgBadgeLock.visibility = View.VISIBLE
            
            holder.txtBadgeStatus.text = context.getString(R.string.badge_locked)
            holder.txtBadgeStatus.setBackgroundColor(Color.parseColor("#F5F5F5")) // Light Grey
            holder.txtBadgeStatus.setTextColor(Color.parseColor("#616161")) // Dark Grey
        }
    }

    override fun getItemCount(): Int = badges.size

    class BadgeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBadgeIcon: AppCompatImageView = itemView.findViewById(R.id.imgBadgeIcon)
        val imgBadgeLock: AppCompatImageView = itemView.findViewById(R.id.imgBadgeLock)
        val txtBadgeName: AppCompatTextView = itemView.findViewById(R.id.txtBadgeName)
        val txtBadgeDesc: AppCompatTextView = itemView.findViewById(R.id.txtBadgeDesc)
        val progressBadge: ProgressBar = itemView.findViewById(R.id.progressBadge)
        val txtBadgeProgress: AppCompatTextView = itemView.findViewById(R.id.txtBadgeProgress)
        val txtBadgeStatus: AppCompatTextView = itemView.findViewById(R.id.txtBadgeStatus)
    }
}
