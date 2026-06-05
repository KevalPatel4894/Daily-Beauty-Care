package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object StretchMarks {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.cocoa_butter)
        listModel.descriptionName = context.getString(R.string.few_min)
        listModel.image = R.drawable.strech_cocobutter
        listModel.details = context.getString(R.string.stretchmarks_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.shea_butter)
        listModel1.descriptionName = context.getString(R.string.few_min)
        listModel1.image = R.drawable.strech_sheabutter
        listModel1.details = context.getString(R.string.stretchmarks_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.aloe_vera)
        listModel2.descriptionName = context.getString(R.string.till_it_dry)
        listModel2.image = R.drawable.aloevera_split
        listModel2.details = context.getString(R.string.stretchmarks_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.vicks_vaporub)
        listModel3.descriptionName = context.getString(R.string.overnight)
        listModel3.image = R.drawable.strech_vicks
        listModel3.details = context.getString(R.string.stretchmarks_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.castor_oil)
        listModel4.descriptionName = context.getString(R.string.fifteen_min)
        listModel4.image = R.drawable.eyebrows_castor
        listModel4.details = context.getString(R.string.stretchmarks_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.egg_whites)
        listModel5.descriptionName = context.getString(R.string.till_it_dry)
        listModel5.image = R.drawable.almondoil_egg
        listModel5.details = context.getString(R.string.stretchmarks_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.lemon_juice)
        listModel6.descriptionName = context.getString(R.string.ten_min)
        listModel6.image = R.drawable.eyebrows_lemon
        listModel6.details = context.getString(R.string.stretchmarks_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.sugar)
        listModel7.descriptionName = context.getString(R.string.few_min)
        listModel7.image = R.drawable.darklips_sugar
        listModel7.details = context.getString(R.string.stretchmarks_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.potato_juice)
        listModel8.descriptionName = context.getString(R.string.few_min)
        listModel8.image = R.drawable.darkcrcl_potato
        listModel8.details = context.getString(R.string.stretchmarks_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.alfalfa)
        listModel9.descriptionName = context.getString(R.string.few_min)
        listModel9.image = R.drawable.strech_alfalfa
        listModel9.details = context.getString(R.string.stretchmarks_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.olive_oil)
        listModel10.descriptionName = context.getString(R.string.thirty_min)
        listModel10.image = R.drawable.lice_oliveoil
        listModel10.details = context.getString(R.string.stretchmarks_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.vitamine_oil)
        listModel11.descriptionName = context.getString(R.string.few_min)
        listModel11.image = R.drawable.wrinkle_vitaminecap
        listModel11.details = context.getString(R.string.stretchmarks_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.coffee_scrub)
        listModel12.descriptionName = context.getString(R.string.five_min)
        listModel12.image = R.drawable.coffee_sugar
        listModel12.details = context.getString(R.string.stretchmarks_remedy_thirteen)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.baking_soda)
        listModel13.descriptionName = context.getString(R.string.thirty_min)
        listModel13.image = R.drawable.baking_soda_dandruff
        listModel13.details = context.getString(R.string.stretchmarks_remedy_fourteen)
        list.add(listModel13)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.skin_stretchmarks_main_one)
        listModel.descriptionName = context.getString(R.string.skin_stretchmarks_detail_one)
        listModel.image = R.drawable.heels_zinc
        listModel.details = context.getString(R.string.stretchmarks_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.skin_stretchmarks_main_two)
        listModel1.descriptionName = context.getString(R.string.skin_stretchmarks_detail_two)
        listModel1.image = R.drawable.lice_vitamineral
        listModel1.details = context.getString(R.string.stretchmarks_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.skin_stretchmarks_main_three)
        listModel2.descriptionName = context.getString(R.string.skin_stretchmarks_detail_three)
        listModel2.image = R.drawable.nailsgrowth_protein
        listModel2.details = context.getString(R.string.stretchmarks_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.skin_stretchmarks_main_four)
        listModel3.descriptionName = context.getString(R.string.skin_stretchmarks_detail_four)
        listModel3.image = R.drawable.omega3_dandruff
        listModel3.details = context.getString(R.string.stretchmarks_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.skin_stretchmarks_main_five)
        listModel4.descriptionName = context.getString(R.string.skin_stretchmarks_detail_five)
        listModel4.image = R.drawable.dandruff_water
        listModel4.details = context.getString(R.string.stretchmarks_diet_five)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.avoid_rapid_weight_gain_lose)
        listModel.descriptionName = context.getString(R.string.niether_gain_loose_rapidly)
        listModel.image = R.drawable.stretch_rapid
        listModel.details = context.getString(R.string.stretchmarks_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.exercise_regularly)
        listModel1.descriptionName = context.getString(R.string.cautions_during_exercise)
        listModel1.image = R.drawable.blackheads_exercise
        listModel1.details = context.getString(R.string.stretchmarks_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.t_pushups)
        listModel2.descriptionName = context.getString(R.string.chest_and_core)
        listModel2.image = R.drawable.stretch_tpushup
        listModel2.details = context.getString(R.string.stretchmarks_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.lateral_leg_circles)
        listModel3.descriptionName = context.getString(R.string.hips_thighs)
        listModel3.image = R.drawable.stretch_legcircle
        listModel3.details = context.getString(R.string.stretchmarks_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.single_leg_pelvic_thrust)
        listModel4.descriptionName = context.getString(R.string.buttocks_glutes)
        listModel4.image = R.drawable.stretch_pelvicthrust
        listModel4.details = context.getString(R.string.stretchmarks_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.pregnancy_stretch_marks)
        listModel5.descriptionName = context.getString(R.string.pregnancy)
        listModel5.image = R.drawable.stretch_pregnancy
        listModel5.details = context.getString(R.string.stretchmarks_exercise_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.additional_tips)
        listModel6.descriptionName = context.getString(R.string.more_tips)
        listModel6.image = R.drawable.strech_title
        listModel6.details = context.getString(R.string.stretchmarks_exercise_seven)
        list.add(listModel6)
        return list
    }
}