package com.kp.beautytips.data.handsfeet

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object CrackedHeels {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.lemon_glycerine)
        listModel.descriptionName = context.getString(R.string.overnight)
        listModel.image = R.drawable.heels_lemonglycerine
        listModel.details = context.getString(R.string.crackedheels_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.vegetable_oil)
        listModel1.descriptionName = context.getString(R.string.overnight)
        listModel1.image = R.drawable.heels_vegetableoil
        listModel1.details = context.getString(R.string.crackedheels_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.banana_avocado)
        listModel2.descriptionName = context.getString(R.string.ten_min)
        listModel2.image = R.drawable.heels_bananaavocado
        listModel2.details = context.getString(R.string.crackedheels_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.vaseline_lemonjuice)
        listModel3.descriptionName = context.getString(R.string.overnight)
        listModel3.image = R.drawable.heels_vaselinelemon
        listModel3.details = context.getString(R.string.crackedheels_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.paraffin_wax)
        listModel4.descriptionName = context.getString(R.string.overnight)
        listModel4.image = R.drawable.heels_parafeen
        listModel4.details = context.getString(R.string.crackedheels_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.honey)
        listModel5.descriptionName = context.getString(R.string.twenty_min)
        listModel5.image = R.drawable.honey
        listModel5.details = context.getString(R.string.crackedheels_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.rice_flour)
        listModel6.descriptionName = context.getString(R.string.few_min)
        listModel6.image = R.drawable.heels_riceflour
        listModel6.details = context.getString(R.string.crackedheels_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.olive_oil)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.lice_oliveoil
        listModel7.details = context.getString(R.string.crackedheels_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.oatmeal)
        listModel8.descriptionName = context.getString(R.string.thirteen_min)
        listModel8.image = R.drawable.dryhands_oatmeal
        listModel8.details = context.getString(R.string.crackedheels_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.sesame_oil)
        listModel9.descriptionName = context.getString(R.string.few_min)
        listModel9.image = R.drawable.lice_seasmeseed
        listModel9.details = context.getString(R.string.crackedheels_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.coconut_oil)
        listModel10.descriptionName = context.getString(R.string.few_min)
        listModel10.image = R.drawable.coconutoil_hairfall_remedy
        listModel10.details = context.getString(R.string.crackedheels_remedy_eleven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.listerine_soak)
        listModel11.descriptionName = context.getString(R.string.twenty_min)
        listModel11.image = R.drawable.heels_listerine
        listModel11.details = context.getString(R.string.crackedheels_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.baking_soda)
        listModel12.descriptionName = context.getString(R.string.fifteen_min)
        listModel12.image = R.drawable.baking_soda_dandruff
        listModel12.details = context.getString(R.string.crackedheels_remedy_thirteen)
        list.add(listModel12)
        val listModel13 = ListModel()
        listModel13.title = context.getString(R.string.apple_cider_vinegar)
        listModel13.descriptionName = context.getString(R.string.fifteen_min)
        listModel13.image = R.drawable.apple_vinegar_dandruff
        listModel13.details = context.getString(R.string.crackedheels_remedy_forteen)
        list.add(listModel13)
        val listModel14 = ListModel()
        listModel14.title = context.getString(R.string.aloe_vera)
        listModel14.descriptionName = context.getString(R.string.overnight)
        listModel14.image = R.drawable.aloevera_split
        listModel14.details = context.getString(R.string.crackedheels_remedy_fifteen)
        list.add(listModel14)
        val listModel15 = ListModel()
        listModel15.title = context.getString(R.string.tea_tree_oil)
        listModel15.descriptionName = context.getString(R.string.two_min)
        listModel15.image = R.drawable.teatreeoil_dandruff
        listModel15.details = context.getString(R.string.crackedheels_remedy_sixteen)
        list.add(listModel15)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.handsfeet_crackedheel_main_one)
        listModel.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_one)
        listModel.image = R.drawable.drydmg_vitamina
        listModel.details = context.getString(R.string.crackedheels_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.handsfeet_crackedheel_main_two)
        listModel1.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_two)
        listModel1.image = R.drawable.shinynail_vitamine
        listModel1.details = context.getString(R.string.crackedheels_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.handsfeet_crackedheel_main_three)
        listModel2.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_three)
        listModel2.image = R.drawable.beautifuleyes_vitaminc
        listModel2.details = context.getString(R.string.crackedheels_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.handsfeet_crackedheel_main_four)
        listModel3.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_four)
        listModel3.image = R.drawable.heels_zinc
        listModel3.details = context.getString(R.string.crackedheels_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.handsfeet_crackedheel_main_five)
        listModel4.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_five)
        listModel4.image = R.drawable.vitaminb_dandruff
        listModel4.details = context.getString(R.string.crackedheels_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.handsfeet_crackedheel_main_six)
        listModel5.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_six)
        listModel5.image = R.drawable.omega3_dandruff
        listModel5.details = context.getString(R.string.crackedheels_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.handsfeet_crackedheel_main_seven)
        listModel6.descriptionName = context.getString(R.string.handsfeet_crackedheel_detail_seven)
        listModel6.image = R.drawable.dandruff_water
        listModel6.details = context.getString(R.string.crackedheels_diet_seven)
        list.add(listModel6)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.moisturize_immediately_after_washing)
        listModel.descriptionName = context.getString(R.string.moisturize_after_bath)
        listModel.image = R.drawable.heels_moisturize
        listModel.details = context.getString(R.string.crackedheels_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.avoid_harsh_soaps)
        listModel1.descriptionName = context.getString(R.string.or_scented_products)
        listModel1.image = R.drawable.handsfeet_avoidsoap
        listModel1.details = context.getString(R.string.crackedheels_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.wear_closed_heals_shoes)
        listModel2.descriptionName = context.getString(R.string.avoid_open_heeled_shoes)
        listModel2.image = R.drawable.heels_closedshoes
        listModel2.details = context.getString(R.string.crackedheels_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.additional_tips)
        listModel3.descriptionName = context.getString(R.string.more_tips)
        listModel3.image = R.drawable.heels_title
        listModel3.details = context.getString(R.string.crackedheels_exercise_four)
        list.add(listModel3)
        return list
    }
}