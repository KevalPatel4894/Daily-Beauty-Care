package com.kp.beautytips.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.ChallengeDetailActivity
import com.kp.beautytips.model.Challenge
import com.kp.beautytips.utils.AppUtils

class ChallengesAdapter(
    private val context: Context,
    private val challenges: List<Challenge>
) : RecyclerView.Adapter<ChallengesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgChallengeCover: AppCompatImageView = view.findViewById(R.id.imgChallengeCover)
        val txtChallengeTitle: AppCompatTextView = view.findViewById(R.id.txtChallengeTitle)
        val txtChallengeDesc: AppCompatTextView = view.findViewById(R.id.txtChallengeDesc)
        val challengeProgressBar: ProgressBar = view.findViewById(R.id.challengeProgressBar)
        val txtChallengeProgress: AppCompatTextView = view.findViewById(R.id.txtChallengeProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_challenge, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val challenge = challenges[position]
        holder.txtChallengeTitle.text = context.getString(challenge.titleResId)
        holder.txtChallengeDesc.text = context.getString(challenge.descriptionResId)
        holder.imgChallengeCover.setImageResource(challenge.coverImage)

        // Calculate progress
        val prefs = context.getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        var completedCount = 0
        for (i in 0 until challenge.daysCount) {
            val isCompleted = prefs.getBoolean("challenge_completed_${challenge.id}_$i", false)
            if (isCompleted) {
                completedCount++
            }
        }

        val progressPercent = (completedCount * 100) / challenge.daysCount
        holder.challengeProgressBar.progress = progressPercent
        holder.txtChallengeProgress.text = context.getString(R.string.challenge_days_progress, completedCount, challenge.daysCount)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChallengeDetailActivity::class.java).apply {
                putExtra("challenge_id", challenge.id)
            }
            context.startActivity(intent)
            AppUtils.startFromRightToLeft(context)
        }
    }

    override fun getItemCount(): Int = challenges.size
}
