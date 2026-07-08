package com.kp.beautytips.model

import java.io.Serializable

data class CustomTip(
    val id: Int = 0,
    val title: String,
    val duration: String,
    val details: String,
    val category: String
) : Serializable
