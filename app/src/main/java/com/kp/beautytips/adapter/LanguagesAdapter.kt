package com.kp.beautytips.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kp.beautytips.R
import com.kp.beautytips.activity.LanguagesActivity
import com.kp.beautytips.model.LanguageModel
import com.kp.beautytips.utils.AppUtils


class LanguagesAdapter(
    languageArrayList: ArrayList<LanguageModel>,
    var languagesActivity: LanguagesActivity,
) : RecyclerView.Adapter<LanguagesAdapter.HomeHolder>() {

    private var languageArrayList = ArrayList<LanguageModel>()
    private var lastChecked: CheckBox? = null
    private var lastCheckedPos = 0
    private val selectedPosition = 0

    init {
        this.languageArrayList = languageArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_language_list, parent, false)
        )
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: HomeHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.languageName.text = languageArrayList[position].langName

        //for default check in first item
        if (AppUtils.getLanguageCode(holder.itemView.context) == languageArrayList[position].langCode) {
            languageArrayList[position].setSelected(true)
            holder.checkBox.isChecked = languageArrayList[position].isSelected
            holder.checkBox.tag = position
            lastCheckedPos = position
        } else {
            languageArrayList[position].setSelected(false)
            holder.checkBox.isChecked = languageArrayList[position].isSelected
            holder.checkBox.tag = position
        }

        holder.checkBox.setOnClickListener { v ->
            if (holder.checkBox.tag != lastCheckedPos) {
                val cb = v as CheckBox
                val clickedPos = (cb.tag as Int).toInt()
                if (cb.isChecked) {
                    if (lastChecked != null) {
                        lastChecked!!.isChecked = false
                        languageArrayList[lastCheckedPos].setSelected(false)
                    }
                    lastChecked = cb
                    lastCheckedPos = clickedPos
                } else lastChecked = null
                languageArrayList[clickedPos].setSelected(cb.isChecked)
                AppUtils.setLanguageCode(
                    holder.checkBox.context,
                    languageArrayList[position].langCode
                )
                languagesActivity.reCreate()
            } else {
                holder.checkBox.isChecked = true
            }
        }

    }

    override fun getItemCount(): Int {
        return languageArrayList.size
    }

    inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var languageName: AppCompatTextView = itemView.findViewById(R.id.tvLanguages)
        var checkBox: AppCompatCheckBox = itemView.findViewById(R.id.checkbox)
    }
}