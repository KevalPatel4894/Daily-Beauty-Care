package com.kp.beautytips.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.kp.beautytips.R
import com.kp.beautytips.model.RoutineModel

class RoutineAdapter(
    private var routines: List<RoutineModel>,
    private val onStartClick: (RoutineModel) -> Unit,
    private val onEditClick: (RoutineModel) -> Unit,
    private val onDeleteClick: (RoutineModel) -> Unit
) : RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>() {

    fun updateData(newRoutines: List<RoutineModel>) {
        routines = newRoutines
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_routine, parent, false)
        return RoutineViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoutineViewHolder, position: Int) {
        val item = routines[position]
        holder.txtTitle.text = item.title

        val stepsCount = item.steps.size
        val totalSec = item.steps.sumOf { it.timerSeconds }
        val mins = Math.max(1, totalSec / 60)
        holder.txtSub.text = "${stepsCount} Steps • ~$mins Mins (${item.type})"

        if (item.type.equals("Night", ignoreCase = true)) {
            holder.imgIcon.setImageResource(R.drawable.ic_clock)
        } else {
            holder.imgIcon.setImageResource(R.drawable.ic_clock)
        }

        holder.btnStart.setOnClickListener { onStartClick(item) }
        holder.btnEdit.setOnClickListener { onEditClick(item) }
        holder.btnDelete.setOnClickListener { onDeleteClick(item) }
    }

    override fun getItemCount(): Int = routines.size

    class RoutineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgIcon: ImageView = itemView.findViewById(R.id.imgRoutineIcon)
        val txtTitle: TextView = itemView.findViewById(R.id.txtRoutineTitle)
        val txtSub: TextView = itemView.findViewById(R.id.txtRoutineSub)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEditRoutine)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDeleteRoutine)
        val btnStart: MaterialButton = itemView.findViewById(R.id.btnStartRoutine)
    }
}
