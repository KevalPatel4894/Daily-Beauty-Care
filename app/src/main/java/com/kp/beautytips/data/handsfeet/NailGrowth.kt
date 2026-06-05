package com.kp.beautytips.data.handsfeet

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object NailGrowth {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.olive_oil)
        listModel.descriptionName = context.getString(R.string.five_min)
        listModel.image = R.drawable.lice_oliveoil
        listModel.details = context.getString(R.string.nailgrowth_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.coconut_oil)
        listModel1.descriptionName = context.getString(R.string.fifteen_min)
        listModel1.image = R.drawable.coconutoil_hairfall_remedy
        listModel1.details = context.getString(R.string.nailgrowth_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.orange_juice)
        listModel2.descriptionName = context.getString(R.string.ten_min)
        listModel2.image = R.drawable.darkcrcl_oranges
        listModel2.details = context.getString(R.string.nailgrowth_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.lemon)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.eyebrows_lemon
        listModel3.details = context.getString(R.string.nailgrowth_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.horsetail)
        listModel4.descriptionName = context.getString(R.string.twenty_min)
        listModel4.image = R.drawable.nailgrowth_horsetail
        listModel4.details = context.getString(R.string.nailgrowth_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.flaxseed_oil)
        listModel5.descriptionName = context.getString(R.string.few_min)
        listModel5.image = R.drawable.nailgrowth_flaxseedoil
        listModel5.details = context.getString(R.string.nailgrowth_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.tomato)
        listModel6.descriptionName = context.getString(R.string.ten_min)
        listModel6.image = R.drawable.tomato_dandruff
        listModel6.details = context.getString(R.string.nailgrowth_remedy_seven)
        list.add(listModel6)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.handsfeet_nailgrowth_main_one)
        listModel.descriptionName = context.getString(R.string.handsfeet_nailgrowth_detail_one)
        listModel.image = R.drawable.eyelashes_biotin
        listModel.details = context.getString(R.string.nailgrowth_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.handsfeet_nailgrowth_main_two)
        listModel1.descriptionName = context.getString(R.string.handsfeet_nailgrowth_detail_two)
        listModel1.image = R.drawable.nailsgrowth_stingingnettle
        listModel1.details = context.getString(R.string.nailgrowth_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.handsfeet_nailgrowth_main_three)
        listModel2.descriptionName = context.getString(R.string.handsfeet_nailgrowth_detail_three)
        listModel2.image = R.drawable.nailsgrowth_protein
        listModel2.details = context.getString(R.string.nailgrowth_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.handsfeet_nailgrowth_main_four)
        listModel3.descriptionName = context.getString(R.string.handsfeet_nailgrowth_detail_four)
        listModel3.image = R.drawable.omega3_dandruff
        listModel3.details = context.getString(R.string.nailgrowth_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.handsfeet_nailgrowth_main_five)
        listModel4.descriptionName = context.getString(R.string.handsfeet_nailgrowth_detail_five)
        listModel4.image = R.drawable.dandruff_water
        listModel4.details = context.getString(R.string.nailgrowth_diet_five)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.dont_cut_cuticles)
        listModel.descriptionName = context.getString(R.string.exfoliate_instead)
        listModel.image = R.drawable.nailsgrowth_cuticles
        listModel.details = context.getString(R.string.nailgrowth_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.dont_use_nails_tools)
        listModel1.descriptionName = context.getString(R.string.dont_risk_bending_nails)
        listModel1.image = R.drawable.nailsgrowth_nailtool
        listModel1.details = context.getString(R.string.nailgrowth_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.dont_peeloff_old_nail_polish)
        listModel2.descriptionName = context.getString(R.string.remove_nailpolish_correctly)
        listModel2.image = R.drawable.nailsgrowth_nailpolish
        listModel2.details = context.getString(R.string.nailgrowth_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.keep_nails_hands_moisturized)
        listModel3.descriptionName = context.getString(R.string.moisturize_them)
        listModel3.image = R.drawable.nailsgrowth_moisturize
        listModel3.details = context.getString(R.string.nailgrowth_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.say_no_fake_nails)
        listModel4.descriptionName = context.getString(R.string.no_fake_nails)
        listModel4.image = R.drawable.nailsgrowth_fakenails
        listModel4.details = context.getString(R.string.nailgrowth_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.additional_tips)
        listModel5.descriptionName = context.getString(R.string.more_tips)
        listModel5.image = R.drawable.nailsgrowth_title
        listModel5.details = context.getString(R.string.nailgrowth_exercise_six)
        list.add(listModel5)
        return list
    }
}