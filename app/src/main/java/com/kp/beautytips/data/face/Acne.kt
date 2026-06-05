package com.kp.beautytips.data.face

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Acne {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.lemon_juice)
        listModel.descriptionName = context.getString(R.string.few_hours)
        listModel.image = R.drawable.ic_lemon_juice
        listModel.details = context.getString(R.string.acne_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.toothpaste)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.ic_toothpaste
        listModel1.details = context.getString(R.string.acne_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.tea_tree_oil)
        listModel2.descriptionName = context.getString(R.string.two_min)
        listModel2.image = R.drawable.ic_tea_tree_oil
        listModel2.details = context.getString(R.string.acne_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.honey_mask)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.honey
        listModel3.details = context.getString(R.string.acne_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.baking_soda)
        listModel4.descriptionName = context.getString(R.string.few_min)
        listModel4.image = R.drawable.baking_soda_dandruff
        listModel4.details = context.getString(R.string.acne_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.oatmeal)
        listModel5.descriptionName = context.getString(R.string.thirty_min)
        listModel5.image = R.drawable.dryhands_oatmeal
        listModel5.details = context.getString(R.string.acne_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.fuller_earth)
        listModel6.descriptionName = context.getString(R.string.till_it_dry)
        listModel6.image = R.drawable.ic_fuller_earth
        listModel6.details = context.getString(R.string.acne_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.orange_peel)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.ic_orance_peal
        listModel7.details = context.getString(R.string.acne_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.aloe_vera_gel)
        listModel8.descriptionName = context.getString(R.string.five_min)
        listModel8.image = R.drawable.aloevera_split
        listModel8.details = context.getString(R.string.acne_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.fenugreek_leaves)
        listModel9.descriptionName = context.getString(R.string.fifteen_min)
        listModel9.image = R.drawable.ic_fenugreek_leaves
        listModel9.details = context.getString(R.string.acne_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.indian_lilac)
        listModel10.descriptionName = context.getString(R.string.twenty_min)
        listModel10.image = R.drawable.ic_neem
        listModel10.details = context.getString(R.string.acne_remedy_eleven)
        list.add(listModel10)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.face_acne_main_one)
        listModel.descriptionName = context.getString(R.string.face_acne_detail_one)
        listModel.image = R.drawable.ic_omega_acid
        listModel.details = context.getString(R.string.acne_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.face_acne_main_two)
        listModel1.descriptionName = context.getString(R.string.face_acne_detail_two)
        listModel1.image = R.drawable.ic_green_tea
        listModel1.details = context.getString(R.string.acne_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.face_acne_main_three)
        listModel2.descriptionName = context.getString(R.string.face_acne_detail_three)
        listModel2.image = R.drawable.ic_juicing
        listModel2.details = context.getString(R.string.acne_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.face_acne_main_four)
        listModel3.descriptionName = context.getString(R.string.face_acne_detail_four)
        listModel3.image = R.drawable.ic_probiotics
        listModel3.details = context.getString(R.string.acne_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.face_acne_main_five)
        listModel4.descriptionName = context.getString(R.string.face_acne_detail_five)
        listModel4.image = R.drawable.ic_zin_rich_food
        listModel4.details = context.getString(R.string.acne_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.face_acne_main_six)
        listModel5.descriptionName = context.getString(R.string.face_acne_detail_six)
        listModel5.image = R.drawable.ic_high_fiber_food
        listModel5.details = context.getString(R.string.acne_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.face_acne_main_seven)
        listModel6.descriptionName = context.getString(R.string.face_acne_detail_seven)
        listModel6.image = R.drawable.ic_sip_water
        listModel6.details = context.getString(R.string.acne_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.face_acne_main_eight)
        listModel7.descriptionName = context.getString(R.string.face_acne_detail_eight)
        listModel7.image = R.drawable.ic_food_avoide
        listModel7.details = context.getString(R.string.acne_diet_eight)
        list.add(listModel7)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.shoulder_stand_yoga)
        listModel.descriptionName = context.getString(R.string.viparita_karani)
        listModel.image = R.drawable.ic_shoulderstand_yoga
        listModel.details = context.getString(R.string.acne_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.the_cobra_pose)
        listModel1.descriptionName = context.getString(R.string.bhujangasana)
        listModel1.image = R.drawable.ic_cobra_pose
        listModel1.details = context.getString(R.string.acne_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.head_knee_pose)
        listModel2.descriptionName = context.getString(R.string.janu_sirsasana)
        listModel2.image = R.drawable.ic_head_to_knee_pose
        listModel2.details = context.getString(R.string.acne_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.the_triangle_pose)
        listModel3.descriptionName = context.getString(R.string.trikonasana)
        listModel3.image = R.drawable.ic_triangle_pose
        listModel3.details = context.getString(R.string.acne_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.the_wind_relieving_pose)
        listModel4.descriptionName = context.getString(R.string.pawanmuktasana)
        listModel4.image = R.drawable.ic_wind_relieving_pose
        listModel4.details = context.getString(R.string.acne_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.toning)
        listModel5.descriptionName = context.getString(R.string.with_acv)
        listModel5.image = R.drawable.ic_toning
        listModel5.details = context.getString(R.string.acne_exercise_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.avoid_sun)
        listModel6.descriptionName = context.getString(R.string.avoid_sun_small)
        listModel6.image = R.drawable.thighs_avoidsun
        listModel6.details = context.getString(R.string.acne_exercise_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.reduce_stress)
        listModel7.descriptionName = context.getString(R.string.sleep_meditate_more_exercise)
        listModel7.image = R.drawable.ic_sleep_properly
        listModel7.details = context.getString(R.string.acne_exercise_eight)
        list.add(listModel7)
        return list
    }
}