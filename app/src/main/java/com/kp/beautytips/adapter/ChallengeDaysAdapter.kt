package com.kp.beautytips.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.kp.beautytips.R
import com.kp.beautytips.activity.DetailsActivity
import com.kp.beautytips.model.ChallengeTask
import com.kp.beautytips.utils.AppUtils

class ChallengeDaysAdapter(
    private val context: Context,
    private val challengeId: String,
    private val tasks: List<ChallengeTask>
) : RecyclerView.Adapter<ChallengeDaysAdapter.ViewHolder>() {

    private var activeDayIndex: Int = 0

    init {
        calculateActiveDay()
    }

    fun calculateActiveDay() {
        val prefs = context.getSharedPreferences("beautytips_prefs", Context.MODE_PRIVATE)
        activeDayIndex = tasks.size // default if all completed
        for (i in tasks.indices) {
            val isCompleted = prefs.getBoolean("challenge_completed_${challengeId}_$i", false)
            if (!isCompleted) {
                activeDayIndex = i
                break
            }
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardChallengeDay: MaterialCardView = view.findViewById(R.id.cardChallengeDay)
        val cardDayCircle: MaterialCardView = view.findViewById(R.id.cardDayCircle)
        val txtDayNumber: AppCompatTextView = view.findViewById(R.id.txtDayNumber)
        val txtDayTitle: AppCompatTextView = view.findViewById(R.id.txtDayTitle)
        val txtDayDuration: AppCompatTextView = view.findViewById(R.id.txtDayDuration)
        val imgDayStatus: AppCompatImageView = view.findViewById(R.id.imgDayStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_challenge_day, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.txtDayNumber.text = task.dayNumber.toString()
        holder.txtDayTitle.text = context.getString(task.titleResId)
        holder.txtDayDuration.text = context.getString(task.durationResId)

        when {
            position < activeDayIndex -> {
                // Completed
                holder.cardDayCircle.setCardBackgroundColor(Color.parseColor("#4CAF50")) // Green
                holder.imgDayStatus.setImageResource(R.drawable.ic_check_circle)
                holder.cardChallengeDay.setStrokeColor(Color.parseColor("#C8E6C9")) // Light Green stroke
                
                holder.itemView.setOnClickListener {
                    launchTaskDetails(task, isCompleted = true, dayIndex = position)
                }
            }
            position == activeDayIndex -> {
                // Active
                holder.cardDayCircle.setCardBackgroundColor(Color.parseColor("#E87A8D")) // Pink
                holder.imgDayStatus.setImageResource(R.drawable.ic_play_circle)
                holder.cardChallengeDay.setStrokeColor(Color.parseColor("#F1B6D6")) // Pink stroke
                
                holder.itemView.setOnClickListener {
                    launchTaskDetails(task, isCompleted = false, dayIndex = position)
                }
            }
            else -> {
                // Locked
                holder.cardDayCircle.setCardBackgroundColor(Color.parseColor("#B0BEC5")) // Grey
                holder.imgDayStatus.setImageResource(R.drawable.ic_lock)
                holder.cardChallengeDay.setStrokeColor(Color.parseColor("#E0E0E0")) // Light Grey stroke
                
                holder.itemView.setOnClickListener {
                    Toast.makeText(context, context.getString(R.string.challenge_day_locked, task.dayNumber), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun launchTaskDetails(task: ChallengeTask, isCompleted: Boolean, dayIndex: Int) {
        val intent = Intent(context, DetailsActivity::class.java).apply {
            putExtra("title", context.getString(task.titleResId))
            putExtra("details", context.getString(task.detailsResId))
            putExtra("image", task.image)
            putExtra("descriptionName", context.getString(task.durationResId))
            putExtra("tabName", context.getString(task.categoryNameResId))
            putExtra("challenge_id", challengeId)
            putExtra("day_index", dayIndex)
            putExtra("challenge_task_completed", isCompleted)
        }
        context.startActivity(intent)
        AppUtils.startFromRightToLeft(context)
    }

    override fun getItemCount(): Int = tasks.size
}
