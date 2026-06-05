package com.kp.beautytips.data.skin

import android.content.Context
import com.kp.beautytips.R
import com.kp.beautytips.model.ListModel

object PricklyHeat {

    fun remedy(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.oatmeal_bath)
        listModel.descriptionName = context.getString(R.string.thirty_min)
        listModel.image = R.drawable.dryhands_oatmeal
        listModel.details = context.getString(R.string.pricklyheat_remedy_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.cold_treatment)
        listModel1.descriptionName = context.getString(R.string.ten_min)
        listModel1.image = R.drawable.puffy_coldwater
        listModel1.details = context.getString(R.string.pricklyheat_remedy_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.sandalwood_powder)
        listModel2.descriptionName = context.getString(R.string.till_it_dry)
        listModel2.image = R.drawable.prickly_sandalwoodpdr
        listModel2.details = context.getString(R.string.pricklyheat_remedy_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.baking_soda)
        listModel3.descriptionName = context.getString(R.string.ten_min)
        listModel3.image = R.drawable.baking_soda_dandruff
        listModel3.details = context.getString(R.string.pricklyheat_remedy_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.fuller_earth)
        listModel4.descriptionName = context.getString(R.string.till_it_dry)
        listModel4.image = R.drawable.underarms_multanimitti
        listModel4.details = context.getString(R.string.pricklyheat_remedy_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.margosa_neem)
        listModel5.descriptionName = context.getString(R.string.twenty_min)
        listModel5.image = R.drawable.teeth_margosa
        listModel5.details = context.getString(R.string.pricklyheat_remedy_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.aloe_vera)
        listModel6.descriptionName = context.getString(R.string.twenty_min)
        listModel6.image = R.drawable.aloevera_split
        listModel6.details = context.getString(R.string.pricklyheat_remedy_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.gram_flour)
        listModel7.descriptionName = context.getString(R.string.fifteen_min)
        listModel7.image = R.drawable.gramflour
        listModel7.details = context.getString(R.string.pricklyheat_remedy_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.cucumber)
        listModel8.descriptionName = context.getString(R.string.thirty_min)
        listModel8.image = R.drawable.darkcrcl_cucumber
        listModel8.details = context.getString(R.string.pricklyheat_remedy_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.raw_potato)
        listModel9.descriptionName = context.getString(R.string.fifteen_min)
        listModel9.image = R.drawable.darkcrcl_potato
        listModel9.details = context.getString(R.string.pricklyheat_remedy_ten)
        list.add(listModel9)
        val listModel10 = ListModel()
        listModel10.title = context.getString(R.string.watermelon)
        listModel10.descriptionName = context.getString(R.string.twenty_min)
        listModel10.image = R.drawable.darkcrcl_watermelon
        listModel10.details = context.getString(R.string.pricklyheat_remedy_elven)
        list.add(listModel10)
        val listModel11 = ListModel()
        listModel11.title = context.getString(R.string.ginger)
        listModel11.descriptionName = context.getString(R.string.twelve_min)
        listModel11.image = R.drawable.prickly_ginger
        listModel11.details = context.getString(R.string.pricklyheat_remedy_twelve)
        list.add(listModel11)
        val listModel12 = ListModel()
        listModel12.title = context.getString(R.string.apple_cider_vinegar)
        listModel12.descriptionName = context.getString(R.string.ten_min)
        listModel12.image = R.drawable.apple_vinegar_dandruff
        listModel12.details = context.getString(R.string.pricklyheat_remedy_thirteen)
        list.add(listModel12)
        return list
    }

    fun diet(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.skin_pricklyheat_main_one)
        listModel.descriptionName = context.getString(R.string.skin_pricklyheat_detail_one)
        listModel.image = R.drawable.prickly_mangoes
        listModel.details = context.getString(R.string.pricklyheat_diet_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.skin_pricklyheat_main_two)
        listModel1.descriptionName = context.getString(R.string.skin_pricklyheat_detail_two)
        listModel1.image = R.drawable.grey_amla_remedy
        listModel1.details = context.getString(R.string.pricklyheat_diet_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.skin_pricklyheat_main_three)
        listModel2.descriptionName = context.getString(R.string.skin_pricklyheat_detail_three)
        listModel2.image = R.drawable.prickly_bottlegourd
        listModel2.details = context.getString(R.string.pricklyheat_diet_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.skin_pricklyheat_main_four)
        listModel3.descriptionName = context.getString(R.string.skin_pricklyheat_detail_four)
        listModel3.image = R.drawable.prickly_ridgegourd
        listModel3.details = context.getString(R.string.pricklyheat_diet_four)
        list.add(listModel3)
        val listModel4 = ListModel()
        listModel4.title = context.getString(R.string.skin_pricklyheat_main_five)
        listModel4.descriptionName = context.getString(R.string.skin_pricklyheat_detail_five)
        listModel4.image = R.drawable.prickly_radish
        listModel4.details = context.getString(R.string.pricklyheat_diet_five)
        list.add(listModel4)
        val listModel5 = ListModel()
        listModel5.title = context.getString(R.string.skin_pricklyheat_main_six)
        listModel5.descriptionName = context.getString(R.string.skin_pricklyheat_detail_six)
        listModel5.image = R.drawable.prickly_whiterice
        listModel5.details = context.getString(R.string.pricklyheat_diet_six)
        list.add(listModel5)
        val listModel6 = ListModel()
        listModel6.title = context.getString(R.string.skin_pricklyheat_main_seven)
        listModel6.descriptionName = context.getString(R.string.skin_pricklyheat_detail_seven)
        listModel6.image = R.drawable.darkcrcl_watermelon
        listModel6.details = context.getString(R.string.pricklyheat_diet_seven)
        list.add(listModel6)
        val listModel7 = ListModel()
        listModel7.title = context.getString(R.string.skin_pricklyheat_main_eight)
        listModel7.descriptionName = context.getString(R.string.skin_pricklyheat_detail_eight)
        listModel7.image = R.drawable.darkcrcl_cucumber
        listModel7.details = context.getString(R.string.pricklyheat_diet_eight)
        list.add(listModel7)
        val listModel8 = ListModel()
        listModel8.title = context.getString(R.string.skin_pricklyheat_main_nine)
        listModel8.descriptionName = context.getString(R.string.skin_pricklyheat_detail_nine)
        listModel8.image = R.drawable.curd_dandruff
        listModel8.details = context.getString(R.string.pricklyheat_diet_nine)
        list.add(listModel8)
        val listModel9 = ListModel()
        listModel9.title = context.getString(R.string.skin_pricklyheat_main_ten)
        listModel9.descriptionName = context.getString(R.string.skin_pricklyheat_detail_ten)
        listModel9.image = R.drawable.darkcrcl_milk
        listModel9.details = context.getString(R.string.pricklyheat_diet_ten)
        list.add(listModel9)
        return list
    }

    fun exercise(context: Context): ArrayList<ListModel> {
        val list = ArrayList<ListModel>()
        val listModel = ListModel()
        listModel.title = context.getString(R.string.air_yourself)
        listModel.descriptionName = context.getString(R.string.expose_prickly_heat_cool_air)
        listModel.image = R.drawable.prickly_airyoursel
        listModel.details = context.getString(R.string.pricklyheat_exercise_one)
        list.add(listModel)
        val listModel1 = ListModel()
        listModel1.title = context.getString(R.string.wear_loose_fitting_cotton_clothes)
        listModel1.descriptionName = context.getString(R.string.allow_proper_passage_air)
        listModel1.image = R.drawable.prickly_loosefitting
        listModel1.details = context.getString(R.string.pricklyheat_exercise_two)
        list.add(listModel1)
        val listModel2 = ListModel()
        listModel2.title = context.getString(R.string.frequent_bathing)
        listModel2.descriptionName = context.getString(R.string.bathing)
        listModel2.image = R.drawable.prickly_bathing
        listModel2.details = context.getString(R.string.pricklyheat_exercise_three)
        list.add(listModel2)
        val listModel3 = ListModel()
        listModel3.title = context.getString(R.string.additional_tips)
        listModel3.descriptionName = context.getString(R.string.more_tips)
        listModel3.image = R.drawable.prickly_title
        listModel3.details = context.getString(R.string.pricklyheat_exercise_four)
        list.add(listModel3)
        return list
    }
}