package com.kp.beautytips.model

class LanguageModel {
    lateinit var langName: String
    var isSelected: Boolean = false
    lateinit var langCode: String

    fun getSelected(): Boolean {
        return this.isSelected
    }

    @JvmName("setSelected1")
    fun setSelected(isSelected: Boolean) {
        this.isSelected = isSelected
    }
}
