package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object Warts {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.garlic)
        listModel.descriptionName = context.getString(R.string.twenty_min)
        listModel.image = R.drawable.garlic_hairfall_remedy
        listModel.details = context.getString(R.string.warts_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.apple_cider_vinegar)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.apple_vinegar_dandruff
        listModel1.details = context.getString(R.string.warts_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.vitaminc_tablets)
        listModel2.descriptionName = context.getString(R.string.four_six_hours)
        listModel2.image = R.drawable.warts_vitaminctab
        listModel2.details = context.getString(R.string.warts_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.hot_water_soak)
        listModel3.descriptionName = context.getString(R.string.few_min)
        listModel3.image = R.drawable.warts_hotwater
        listModel3.details = context.getString(R.string.warts_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.baking_soda)
        listModel4.descriptionName = context.getString(R.string.overnight)
        listModel4.image = R.drawable.baking_soda_dandruff
        listModel4.details = context.getString(R.string.warts_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.aloe_vera)
        listModel5.descriptionName = context.getString(R.string.four_six_hours)
        listModel5.image = R.drawable.aloevera_split
        listModel5.details = context.getString(R.string.warts_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.tea_tree_oil)
        listModel6.descriptionName = context.getString(R.string.overnight)
        listModel6.image = R.drawable.teatreeoil_dandruff
        listModel6.details = context.getString(R.string.warts_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.castor_oil)
        listModel7.descriptionName = context.getString(R.string.few_min)
        listModel7.image = R.drawable.eyebrows_castor
        listModel7.details = context.getString(R.string.warts_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.banana_peel)
        listModel8.descriptionName = context.getString(R.string.overnight)
        listModel8.image = R.drawable.banana_peel
        listModel8.details = context.getString(R.string.warts_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.basil)
        listModel9.descriptionName = context.getString(R.string.rub_with_it)
        listModel9.image = R.drawable.teeth_basil
        listModel9.details = context.getString(R.string.warts_remedy_ten)
        list.add(listModel9)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.skin_warts_main_one)
        listModel.descriptionName = context.getString(R.string.skin_warts_detail_one)
        listModel.image = R.drawable.dryhand_leafyveg
        listModel.details = context.getString(R.string.warts_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.skin_warts_main_two)
        listModel1.descriptionName = context.getString(R.string.skin_warts_detail_two)
        listModel1.image = R.drawable.warts_fruits
        listModel1.details = context.getString(R.string.warts_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.skin_warts_main_three)
        listModel2.descriptionName = context.getString(R.string.skin_warts_detail_three)
        listModel2.image = R.drawable.warts_herbs
        listModel2.details = context.getString(R.string.warts_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.skin_warts_main_four)
        listModel3.descriptionName = context.getString(R.string.skin_warts_detail_four)
        listModel3.image = R.drawable.lice_protein
        listModel3.details = context.getString(R.string.warts_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.skin_warts_main_five)
        listModel4.descriptionName = context.getString(R.string.skin_warts_detail_five)
        listModel4.image = R.drawable.blackheads_avoidfood
        listModel4.details = context.getString(R.string.warts_diet_five)
        list.add(listModel4)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.duct_tape)
        listModel.descriptionName = context.getString(R.string.black_electric_tape_gorilla_tape)
        listModel.image = R.drawable.warts_tape
        listModel.details = context.getString(R.string.warts_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.boost_immune_system)
        listModel1.descriptionName = context.getString(R.string.increase_immunity)
        listModel1.image = R.drawable.warts_immune
        listModel1.details = context.getString(R.string.warts_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.nail_polish)
        listModel2.descriptionName = context.getString(R.string.works_as_duct_tape)
        listModel2.image = R.drawable.warts_nailpolish
        listModel2.details = context.getString(R.string.warts_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.chalk_superglue)
        listModel3.descriptionName = context.getString(R.string.cut_off_oxygen)
        listModel3.image = R.drawable.warts_chalk
        listModel3.details = context.getString(R.string.warts_exercise_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.bring_heat)
        listModel4.descriptionName = context.getString(R.string.heatpad_hot_water)
        listModel4.image = R.drawable.warts_heatingpad
        listModel4.details = context.getString(R.string.warts_exercise_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.spit_on_them)
        listModel5.descriptionName = context.getString(R.string.morning_spit)
        listModel5.image = R.drawable.warts_spitting
        listModel5.details = context.getString(R.string.warts_exercise_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.snitch_on_warts)
        listModel6.descriptionName = context.getString(R.string.snitch_them)
        listModel6.image = R.drawable.warts_title
        listModel6.details = context.getString(R.string.warts_exercise_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.stop_spread)
        listModel7.descriptionName = context.getString(R.string.dont_spread_virus)
        listModel7.image = R.drawable.warts_stopspread
        listModel7.details = context.getString(R.string.warts_exercise_eight)
        list.add(listModel7)
        return list
    }
}