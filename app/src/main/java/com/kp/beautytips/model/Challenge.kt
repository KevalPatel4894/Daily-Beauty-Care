package com.kp.beautytips.model

data class Challenge(
    val id: String,
    val titleResId: Int,
    val descriptionResId: Int,
    val daysCount: Int,
    val coverImage: Int,
    val tasks: List<ChallengeTask>
)

data class ChallengeTask(
    val dayNumber: Int,
    val titleResId: Int,
    val detailsResId: Int,
    val durationResId: Int,
    val image: Int,
    val categoryNameResId: Int
)
