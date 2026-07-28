package com.kp.beautytips.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
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
    private var lastChecked: CompoundButton? = null
    private var lastCheckedPos = -1

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

        val currentLangCode = AppUtils.getLanguageCode(holder.itemView.context)
        val isSelected = (currentLangCode == languageArrayList[position].langCode)
        languageArrayList[position].setSelected(isSelected)
        holder.checkBox.isChecked = isSelected
        holder.checkBox.tag = position

        if (isSelected) {
            lastChecked = holder.checkBox
            lastCheckedPos = position
        }

        val selectLanguageAction = {
            if (position != lastCheckedPos) {
                lastChecked?.isChecked = false
                if (lastCheckedPos in 0 until languageArrayList.size) {
                    languageArrayList[lastCheckedPos].setSelected(false)
                }

                holder.checkBox.isChecked = true
                languageArrayList[position].setSelected(true)
                lastChecked = holder.checkBox
                lastCheckedPos = position

                AppUtils.setLanguageCode(
                    holder.checkBox.context,
                    languageArrayList[position].langCode
                )
                languagesActivity.reCreate()
            } else {
                holder.checkBox.isChecked = true
            }
        }

        holder.checkBox.setOnClickListener { selectLanguageAction() }
        holder.itemView.setOnClickListener { selectLanguageAction() }
    }

    override fun getItemCount(): Int {
        return languageArrayList.size
    }

    inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var languageName: AppCompatTextView = itemView.findViewById(R.id.tvLanguages)
        var checkBox: CompoundButton = itemView.findViewById(R.id.checkbox)
    }
}