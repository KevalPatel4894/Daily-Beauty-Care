package com.kp.beautytips.model

data class DiaryEntry(
    val id: Int,
    val date: String,
    val title: String,
    val content: String,
    val imagePath: String?
)
