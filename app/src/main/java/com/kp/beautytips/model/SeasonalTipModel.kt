package com.kp.beautytips.model

import java.io.Serializable

data class SeasonalTipModel(
    val title: String,
    val description: String
) : Serializable

data class SeasonCategory(
    val seasonKey: String, // "summer", "monsoon", "autumn", "winter"
    val seasonNameRes: Int,
    val headerTitleRes: Int,
    val headerDescRes: Int,
    val tips: List<SeasonalTipModel>
) : Serializable
