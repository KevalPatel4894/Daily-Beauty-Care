package com.kp.beautytips.model

import java.io.Serializable

data class RoutineStep(
    val stepName: String,
    val description: String = "",
    val timerSeconds: Int = 0
) : Serializable

data class RoutineModel(
    val id: Int = 0,
    val title: String,
    val type: String, // "Morning", "Night", or "Custom"
    val steps: List<RoutineStep> = emptyList()
) : Serializable
