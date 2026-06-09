package com.kp.beautytips.data

import com.kp.beautytips.R
import com.kp.beautytips.model.Challenge
import com.kp.beautytips.model.ChallengeTask

object ChallengeRepository {
    val challenges: List<Challenge> = listOf(
        Challenge(
            id = "glowing_skin",
            titleResId = R.string.challenge_glow_title,
            descriptionResId = R.string.challenge_glow_desc,
            daysCount = 7,
            coverImage = R.drawable.glowingskin_title,
            tasks = listOf(
                ChallengeTask(1, R.string.challenge_task_glowing_skin_day1_title, R.string.challenge_task_glowing_skin_day1_details, R.string.duration_15_min, R.drawable.honey, R.string.challenge_cat_face),
                ChallengeTask(2, R.string.challenge_task_glowing_skin_day2_title, R.string.challenge_task_glowing_skin_day2_details, R.string.duration_5_min, R.drawable.aloevera_split, R.string.challenge_cat_face),
                ChallengeTask(3, R.string.challenge_task_glowing_skin_day3_title, R.string.challenge_task_glowing_skin_day3_details, R.string.duration_15_min, R.drawable.cucumber_milk, R.string.challenge_cat_face),
                ChallengeTask(4, R.string.challenge_task_glowing_skin_day4_title, R.string.challenge_task_glowing_skin_day4_details, R.string.duration_10_min, R.drawable.cucumber_lemon, R.string.challenge_cat_face),
                ChallengeTask(5, R.string.challenge_task_glowing_skin_day5_title, R.string.challenge_task_glowing_skin_day5_details, R.string.duration_15_min, R.drawable.papaya_turmeric, R.string.challenge_cat_face),
                ChallengeTask(6, R.string.challenge_task_glowing_skin_day6_title, R.string.challenge_task_glowing_skin_day6_details, R.string.duration_2_min, R.drawable.baking_soda_dandruff, R.string.challenge_cat_face),
                ChallengeTask(7, R.string.challenge_task_glowing_skin_day7_title, R.string.challenge_task_glowing_skin_day7_details, R.string.duration_20_min, R.drawable.glowing_safron, R.string.challenge_cat_face)
            )
        ),
        Challenge(
            id = "healthy_hair",
            titleResId = R.string.challenge_hair_title,
            descriptionResId = R.string.challenge_hair_desc,
            daysCount = 7,
            coverImage = R.drawable.hairfall_title,
            tasks = listOf(
                ChallengeTask(1, R.string.challenge_task_healthy_hair_day1_title, R.string.challenge_task_healthy_hair_day1_details, R.string.duration_10_min, R.drawable.coconutoil_hairfall_remedy, R.string.challenge_cat_hair),
                ChallengeTask(2, R.string.challenge_task_healthy_hair_day2_title, R.string.challenge_task_healthy_hair_day2_details, R.string.duration_5_min, R.drawable.coconutoil_lemon_dandruff, R.string.challenge_cat_hair),
                ChallengeTask(3, R.string.challenge_task_healthy_hair_day3_title, R.string.challenge_task_healthy_hair_day3_details, R.string.duration_30_min, R.drawable.curd_dandruff, R.string.challenge_cat_hair),
                ChallengeTask(4, R.string.challenge_task_healthy_hair_day4_title, R.string.challenge_task_healthy_hair_day4_details, R.string.duration_15_min, R.drawable.apple_vinegar_dandruff, R.string.challenge_cat_hair),
                ChallengeTask(5, R.string.challenge_task_healthy_hair_day5_title, R.string.challenge_task_healthy_hair_day5_details, R.string.duration_30_min, R.drawable.aloevera_split, R.string.challenge_cat_hair),
                ChallengeTask(6, R.string.challenge_task_healthy_hair_day6_title, R.string.challenge_task_healthy_hair_day6_details, R.string.duration_5_min, R.drawable.baking_soda_dandruff, R.string.challenge_cat_hair),
                ChallengeTask(7, R.string.challenge_task_healthy_hair_day7_title, R.string.challenge_task_healthy_hair_day7_details, R.string.duration_5_min, R.drawable.neemjuice_dandruff, R.string.challenge_cat_hair)
            )
        ),
        Challenge(
            id = "bright_eyes",
            titleResId = R.string.challenge_eyes_title,
            descriptionResId = R.string.challenge_eyes_desc,
            daysCount = 7,
            coverImage = R.drawable.darkcircle_title,
            tasks = listOf(
                ChallengeTask(1, R.string.challenge_task_bright_eyes_day1_title, R.string.challenge_task_bright_eyes_day1_details, R.string.duration_15_min, R.drawable.darkcrcl_cucumber, R.string.challenge_cat_eye),
                ChallengeTask(2, R.string.challenge_task_bright_eyes_day2_title, R.string.challenge_task_bright_eyes_day2_details, R.string.duration_5_min, R.drawable.darkcrcl_almondoil, R.string.challenge_cat_eye),
                ChallengeTask(3, R.string.challenge_task_bright_eyes_day3_title, R.string.challenge_task_bright_eyes_day3_details, R.string.duration_10_min, R.drawable.darkcrcl_teabag, R.string.challenge_cat_eye),
                ChallengeTask(4, R.string.challenge_task_bright_eyes_day4_title, R.string.challenge_task_bright_eyes_day4_details, R.string.duration_5_min, R.drawable.beautifuleyes_exercisepalms, R.string.challenge_cat_eye),
                ChallengeTask(5, R.string.challenge_task_bright_eyes_day5_title, R.string.challenge_task_bright_eyes_day5_details, R.string.duration_15_min, R.drawable.darkcrcl_rosewater, R.string.challenge_cat_eye),
                ChallengeTask(6, R.string.challenge_task_bright_eyes_day6_title, R.string.challenge_task_bright_eyes_day6_details, R.string.duration_15_min, R.drawable.darkcrcl_aloeveragel, R.string.challenge_cat_eye),
                ChallengeTask(7, R.string.challenge_task_bright_eyes_day7_title, R.string.challenge_task_bright_eyes_day7_details, R.string.duration_5_min, R.drawable.beautifuleyes_exercisepen, R.string.challenge_cat_eye)
            )
        )
    )

    fun getChallengeById(id: String): Challenge? {
        return challenges.find { it.id == id }
    }
}
