package com.kp.beautytips.data.hair

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object DryAndDamagedHair {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.avocado_treatment)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.avocado_split
        listModel.details = context.getString(R.string.drydmghair_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.massage_butter)
        listModel1.descriptionName = context.getString(R.string.thirty_min)
        listModel1.image = R.drawable.drydmg_butter
        listModel1.details = context.getString(R.string.drydmghair_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.condition_olive_oil)
        listModel2.descriptionName = context.getString(R.string.thirty_min)
        listModel2.image = R.drawable.lice_oliveoil
        listModel2.details = context.getString(R.string.drydmghair_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.rinse_tea)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.grey_blacktea_remedy
        listModel3.details = context.getString(R.string.drydmghair_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.apple_cider_vinegar)
        listModel4.descriptionName = context.getString(R.string.thirty_min)
        listModel4.image = R.drawable.apple_vinegar_dandruff
        listModel4.details = context.getString(R.string.drydmghair_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.shampoo_omelet)
        listModel5.descriptionName = context.getString(R.string.five_min)
        listModel5.image = R.drawable.eggmask_split
        listModel5.details = context.getString(R.string.drydmghair_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.hibiscus)
        listModel6.descriptionName = context.getString(R.string.few_hours)
        listModel6.image = R.drawable.hibiscus_hairfall_remedy
        listModel6.details = context.getString(R.string.drydmghair_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.treat_botanical_oils)
        listModel7.descriptionName = context.getString(R.string.thirty_min)
        listModel7.image = R.drawable.drydmg_botanical
        listModel7.details = context.getString(R.string.drydmghair_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.sandalwood_oil)
        listModel8.descriptionName = context.getString(R.string.instant)
        listModel8.image = R.drawable.drydmg_sandlewood
        listModel8.details = context.getString(R.string.drydmghair_remedy_nine)
        list.add(listModel8)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.hair_drydmghair_main_one)
        listModel.descriptionName = context.getString(R.string.hair_drydmghair_detail_one)
        listModel.image = R.drawable.lice_protein
        listModel.details = context.getString(R.string.drydmghair_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.hair_drydmghair_main_two)
        listModel1.descriptionName = context.getString(R.string.hair_drydmghair_detail_two)
        listModel1.image = R.drawable.drydmg_vitamina
        listModel1.details = context.getString(R.string.drydmghair_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.hair_drydmghair_main_three)
        listModel2.descriptionName = context.getString(R.string.hair_drydmghair_detail_three)
        listModel2.image = R.drawable.drydmg_vitaminb5
        listModel2.details = context.getString(R.string.drydmghair_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hair_drydmghair_main_four)
        listModel3.descriptionName = context.getString(R.string.hair_drydmghair_detail_four)
        listModel3.image = R.drawable.drydmg_healthyfats
        listModel3.details = context.getString(R.string.drydmghair_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.hair_drydmghair_main_five)
        listModel4.descriptionName = context.getString(R.string.hair_drydmghair_main_five)
        listModel4.image = R.drawable.reducesugar_dandruff
        listModel4.details = context.getString(R.string.drydmghair_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.hair_drydmghair_main_six)
        listModel5.descriptionName = context.getString(R.string.hair_drydmghair_main_six)
        listModel5.image = R.drawable.dandruff_water
        listModel5.details = context.getString(R.string.drydmghair_diet_six)
        list.add(listModel5)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.accupressure_massage)
        listModel.descriptionName = context.getString(R.string.best_results_olive_oil)
        listModel.image = R.drawable.drydmg_accupresuremsg
        listModel.details = context.getString(R.string.drydmghair_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.shenxue_accupressure)
        listModel1.descriptionName = context.getString(R.string.accupressure_point_palm_side)
        listModel1.image = R.drawable.shenxue_split
        listModel1.details = context.getString(R.string.drydmghair_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.paihui_accupressure)
        listModel2.descriptionName = context.getString(R.string.accupressure_point_top_of_head)
        listModel2.image = R.drawable.paihui_split
        listModel2.details = context.getString(R.string.drydmghair_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.wrap_wet_hair_dry)
        listModel3.descriptionName = context.getString(R.string.wrap_up_damaged_hair)
        listModel3.image = R.drawable.drydmg_wethair
        listModel3.details = context.getString(R.string.drydmghair_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.use_natural_exfoliants)
        listModel4.descriptionName = context.getString(R.string.keep_the_air_dry)
        listModel4.image = R.drawable.drydmg_humidifier
        listModel4.details = context.getString(R.string.drydmghair_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.let_hair_down)
        listModel5.descriptionName = context.getString(R.string.give_hair_break)
        listModel5.image = R.drawable.drydmg_hairloose
        listModel5.details = context.getString(R.string.drydmghair_exercise_six)
        list.add(listModel5)
        return list
    }
}