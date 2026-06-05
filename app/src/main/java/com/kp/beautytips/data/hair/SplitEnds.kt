package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object SplitEnds {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.egg_mask)
        listModel.descriptionName = context.getString(R.string.thirty_min)
        listModel.image = R.drawable.eggmask_split
        listModel.details = context.getString(R.string.splitend_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.papaya_yogurt)
        listModel1.descriptionName = context.getString(R.string.thirty_min)
        listModel1.image = R.drawable.papayayogurt_split
        listModel1.details = context.getString(R.string.splitend_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.honey)
        listModel2.descriptionName = context.getString(R.string.ten_min)
        listModel2.image = R.drawable.honey
        listModel2.details = context.getString(R.string.splitend_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.beer)
        listModel3.descriptionName = context.getString(R.string.fortyfive_min)
        listModel3.image = R.drawable.beer_split
        listModel3.details = context.getString(R.string.splitend_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.banana_pack)
        listModel4.descriptionName = context.getString(R.string.three_min)
        listModel4.image = R.drawable.banana_split
        listModel4.details = context.getString(R.string.splitend_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.mayonnaise)
        listModel5.descriptionName = context.getString(R.string.one_hr)
        listModel5.image = R.drawable.mayonnaise_split
        listModel5.details = context.getString(R.string.splitend_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.avocado_pack)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.avocado_split
        listModel6.details = context.getString(R.string.splitend_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.aloe_vera)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.aloevera_split
        listModel7.details = context.getString(R.string.splitend_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.hot_oil_deep_conditioner)
        listModel8.descriptionName = context.getString(R.string.forty_min)
        listModel8.image = R.drawable.hotoil_split
        listModel8.details = context.getString(R.string.splitend_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_splitend_main_one)
        listModel.descriptionName = context.getString(R.string.hair_splitend_detail_one)
        listModel.image = R.drawable.egg_dandruff
        listModel.details = context.getString(R.string.splitend_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_splitend_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_splitend_detail_two)
        listModel1.image = R.drawable.walnut_dandruff
        listModel1.details = context.getString(R.string.splitend_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_splitend_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_splitend_detail_three)
        listModel2.image = R.drawable.tomato_dandruff
        listModel2.details = context.getString(R.string.splitend_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_splitend_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_splitend_detail_four)
        listModel3.image = R.drawable.sunflower_dandruff
        listModel3.details = context.getString(R.string.splitend_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_splitend_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_splitend_detail_five)
        listModel4.image = R.drawable.spinach_dandruff
        listModel4.details = context.getString(R.string.splitend_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.hair_splitend_main_six)
        listModel5.descriptionName = context.getString(R.string.hair_splitend_detail_six)
        listModel5.image = R.drawable.curd_dandruff
        listModel5.details = context.getString(R.string.splitend_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.hair_splitend_main_seven)
        listModel6.descriptionName = context.getString(R.string.hair_splitend_detail_seven)
        listModel6.image = R.drawable.avocado_split
        listModel6.details = context.getString(R.string.splitend_diet_seven)
        list.add(listModel6)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.shenxue_accupressure)
        listModel.descriptionName = context.getString(R.string.accupressure_point_palm_side)
        listModel.image = R.drawable.shenxue_split
        listModel.details = context.getString(R.string.splitend_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.paihui_accupressure)
        listModel1.descriptionName = context.getString(R.string.accupressure_point_top_of_head)
        listModel1.image = R.drawable.paihui_split
        listModel1.details = context.getString(R.string.splitend_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.rubbing_nails)
        listModel2.descriptionName = context.getString(R.string.balyam_yoga)
        listModel2.image = R.drawable.dandruff_nailrubbing
        listModel2.details = context.getString(R.string.splitend_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.accupressure_massage)
        listModel3.descriptionName = context.getString(R.string.best_results_olive_oil)
        listModel3.image = R.drawable.ic_facial_acupuncture
        listModel3.details = context.getString(R.string.splitend_exercise_four)
        list.add(listModel3)
        return list
    }
}